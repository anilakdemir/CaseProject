package com.anilakdemir.ecommerce.service.impl;

import com.anilakdemir.ecommerce.model.Campaign;
import com.anilakdemir.ecommerce.model.Coupon;
import com.anilakdemir.ecommerce.model.ProductOrder;
import com.anilakdemir.ecommerce.model.ShoppingCart;
import com.anilakdemir.ecommerce.repository.ShoppingCartRepository;
import com.anilakdemir.ecommerce.service.ShoppingCartService;
import com.anilakdemir.ecommerce.util.DiscountCalculator;
import com.anilakdemir.ecommerce.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        if(shoppingCart.getCouponDiscount() == null)
            shoppingCart.setCouponDiscount(0.0);
        if(shoppingCart.getTotalAmount() == null)
            shoppingCart.setTotalAmount(0.0);
        if(shoppingCart.getCampaignDiscount() == null)
            shoppingCart.setCampaignDiscount(0.0);
        if(shoppingCart.getAmountAfterDiscounts() == null)
            shoppingCart.setAmountAfterDiscounts(0.0);
        if(shoppingCart.getDeliveryCost() == null)
            shoppingCart.setDeliveryCost(0.0);
        if(shoppingCart.getProductOrders() == null)
            shoppingCart.setProductOrders(new ArrayList<>());
        if(shoppingCart.getProductOrders() != null && !shoppingCart.getProductOrders().isEmpty()) {
            Double totalCartAmount = 0.0;
            for(ProductOrder order: shoppingCart.getProductOrders())
                totalCartAmount += order.getOrderPrice();
            shoppingCart.setTotalAmount(totalCartAmount);
            shoppingCart.setAmountAfterDiscounts(totalCartAmount - shoppingCart.getCampaignDiscount() - shoppingCart.getCouponDiscount());
        }
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void applyCampaignDiscounts(Long cartId, List<Campaign> campaigns) {

        ShoppingCart cart = shoppingCartRepository.findById(cartId).orElse(null);
        if(cart == null)
            return;

        Pair<Campaign, Double> campaignDiscount = DiscountCalculator.calculateCampaignDiscount(cart.getProductOrders(), campaigns);
        cart.setAppliedCampaign(campaignDiscount.getFirst());
        cart.setCampaignDiscount(campaignDiscount.getSecond());
        cart.setAmountAfterDiscounts(cart.getTotalAmount() - cart.getCampaignDiscount() - cart.getCouponDiscount());

        shoppingCartRepository.save(cart);
    }

    @Override
    public void applyCouponDiscount(Long cartId, Coupon coupon) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId).orElse(null);
        if(cart == null)
            return;

        Double couponDiscount = DiscountCalculator.calculateCouponDiscount(cart.getTotalAmount(), cart.getAmountAfterDiscounts(), coupon);

        if(couponDiscount >= 0){
            cart.setCouponDiscount(couponDiscount);
            cart.setAppliedCoupon(coupon);
            cart.setAmountAfterDiscounts(cart.getAmountAfterDiscounts() - cart.getCouponDiscount());

            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public void deleteById(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
