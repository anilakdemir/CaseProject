package com.anilakdemir.ecommerce.controller;

import com.anilakdemir.ecommerce.model.*;
import com.anilakdemir.ecommerce.service.*;
import com.anilakdemir.ecommerce.util.DiscountCalculator;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductOrderService productOrderService;
    private final ProductService productService;
    private final CouponService couponService;
    private final CampaignService campaignService;
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductOrderService productOrderService, ProductService productService, CouponService couponService, CampaignService campaignService) {
        this.shoppingCartService = shoppingCartService;
        this.productOrderService = productOrderService;
        this.productService = productService;
        this.couponService = couponService;
        this.campaignService = campaignService;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<ShoppingCart> getAllShoppingCarts(){
        return shoppingCartService.findAll();
    }

    @PostMapping(value = "/cart", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
    @ResponseBody
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.save(shoppingCart);
    }

    @RequestMapping(value = "/cart/{cartId}/applyCampaignDiscount", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public Optional<ShoppingCart> applyCampaignDiscount(@PathVariable Long cartId, @RequestBody List<Campaign> campaigns){
        if(campaigns == null && campaigns.isEmpty()) return Optional.of(null);
        List<Campaign> campaignsFromDB = campaignService.findByIdList(campaigns);
        shoppingCartService.applyCampaignDiscounts(cartId, campaignsFromDB);
        return Optional.of(shoppingCartService.findById(cartId).get());
    }

    @RequestMapping(value = "/cart/{cartId}/applyCouponDiscount", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public Optional<ShoppingCart> applyCouponDiscount(@PathVariable Long cartId, @RequestBody Coupon coupon){
        if(coupon == null || coupon.getId() == null) return null;
        Coupon couponFromDB = couponService.findById(coupon.getId()).get();
        shoppingCartService.applyCouponDiscount(cartId, couponFromDB);
        return Optional.of(shoppingCartService.findById(cartId).get());
    }

    @GetMapping(value = "/cart/{cartId}",produces = "application/json")
    @ResponseBody
    public Optional<ShoppingCart> getCartById(@PathVariable Long cartId) {
        return Optional.of(shoppingCartService.findById(cartId).get());
    }

    @DeleteMapping(value = "/cart/{cartId}",produces = "application/json")
    @ResponseBody
    public void deleteCartById(@PathVariable Long cartId) {
        shoppingCartService.deleteById(cartId);
    }

    @GetMapping(value = "/cart/{id}/orders",produces = "application/json")
    @ResponseBody
    public List<ProductOrder> getProductOrders(@PathVariable Long id) {
        return shoppingCartService.findById(id).get().getProductOrders();
    }

    @GetMapping(value = "/cart/{cartId}/orders/{orderId}",produces = "application/json")
    @ResponseBody
    public ProductOrder getProductOrder(@PathVariable Long cartId, @PathVariable Long orderId) {
        List<ProductOrder> orders = shoppingCartService.findById(cartId).get().getProductOrders();
        return orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().get();
    }


    @PostMapping(value = "/cart/{cartId}/order", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
    @ResponseBody
    public ShoppingCart addOrder(@PathVariable Long cartId, @RequestBody ProductOrder order) {
        // save new order get it if saving is successful
        ProductOrder savedOrder = productOrderService.save(order);

        // get cart from database
        ShoppingCart cart = shoppingCartService.findById(cartId).get();

        // get existing order list
        List<ProductOrder> orders = cart.getProductOrders();

        if(orders == null)
            orders = new ArrayList<>();

        // add new order to existing order list
        orders.add(savedOrder);

        // set updated order list
        cart.setProductOrders(orders);

        // find product with id
        Product orderedProduct = productService.findById(savedOrder.getProduct().getId()).get();

        // calculate new total cart amount
        double orderAmount = (double) (orderedProduct.getPrice() * savedOrder.getQuantity());

        // get existing total cart amount
        double totalCartAmount = cart.getTotalAmount();

        // update existing total cart amount
        totalCartAmount += orderAmount;

        // set new total cart amount
        cart.setTotalAmount(totalCartAmount);
        cart.setAmountAfterDiscounts(cart.getTotalAmount() - cart.getCampaignDiscount() - cart.getCouponDiscount());

        // save and return updated cart
        return shoppingCartService.save(cart);
    }
}
