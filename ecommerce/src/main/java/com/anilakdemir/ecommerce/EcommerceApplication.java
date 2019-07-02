package com.anilakdemir.ecommerce;

import com.anilakdemir.ecommerce.model.*;
import com.anilakdemir.ecommerce.service.*;
import com.anilakdemir.ecommerce.util.DeliveryCostCalculator;
import com.anilakdemir.ecommerce.util.DiscountCalculator;
import com.anilakdemir.ecommerce.util.Pair;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			CategoryService categoryService,
			ProductService productService,
			CampaignService campaignService,
			CouponService couponService,
			ShoppingCartService shoppingCartService,
			ProductOrderService productOrderService
	) {

		return args -> {
			Category electronic = new Category("Electronic", null);
			Category smartPhone = new Category("Smart Phone", electronic);
			Category tv = new Category("TV", electronic);
			Category computer = new Category("Computer", electronic);
			Category cosmetic = new Category("Cosmetic", null);
			Category home = new Category("Home", null);
			Category kitchen = new Category("Kitchen", home);
			categoryService.save(electronic);
			categoryService.save(tv);
			categoryService.save(computer);
			categoryService.save(smartPhone);
			categoryService.save(cosmetic);
			categoryService.save(home);
			categoryService.save(kitchen);

			Product samsungA5032GB = new Product("Samsung A50 32GB (İthalatçı Garantili)", 1329.0, smartPhone);
			Product iPhoneXS64GBGold = new Product("iPhone XS 64GB Altın", 9165.0, smartPhone);
			Product ceramicPlate = new Product("El Yapımı 6'lı Seramik Pasta Servis Seti", 79.99, kitchen);
			productService.save(samsungA5032GB);
			productService.save(iPhoneXS64GBGold);
			productService.save(ceramicPlate);

			Campaign campaignForSmartPhone = new Campaign(smartPhone, 20.0, 2, DiscountType.RATE);
			Campaign campaignForKitchen = new Campaign(kitchen, 30.0, 2, DiscountType.AMOUNT);
			Campaign campaignForHome = new Campaign(home, 20.0, 2, DiscountType.RATE);
			campaignService.save(campaignForSmartPhone);
			campaignService.save(campaignForKitchen);
			campaignService.save(campaignForHome);

			Coupon welcome = new Coupon(50, 250.0, DiscountType.AMOUNT);
			Coupon welcome2 = new Coupon(10, 500.0, DiscountType.RATE);

			couponService.save(welcome);
			couponService.save(welcome2);


			ProductOrder order1 = new ProductOrder(samsungA5032GB, 2);
			ProductOrder order2 = new ProductOrder(iPhoneXS64GBGold,3);
			ProductOrder order3 = new ProductOrder(ceramicPlate, 1);
			productOrderService.save(order1);
			productOrderService.save(order2);
			productOrderService.save(order3);

			ShoppingCart cart = new ShoppingCart();

			List<ProductOrder> orderList = new ArrayList<>();
			orderList.add(order1);
			orderList.add(order2);
			orderList.add(order3);
			cart.setProductOrders(orderList);

			DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(1.0, 0.50, 2.99);
			Double deliveryCost = deliveryCostCalculator.calculateFor(cart);
			cart.setDeliveryCost(deliveryCost);

			shoppingCartService.save(cart);
		};
	}
}
