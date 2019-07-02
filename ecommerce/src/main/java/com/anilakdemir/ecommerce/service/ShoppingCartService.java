package com.anilakdemir.ecommerce.service;

import com.anilakdemir.ecommerce.model.Campaign;
import com.anilakdemir.ecommerce.model.Coupon;
import com.anilakdemir.ecommerce.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> findById(Long id);

    List<ShoppingCart> findAll();

    ShoppingCart save(ShoppingCart shoppingCart);

    void applyCampaignDiscounts(Long cartId, List<Campaign> campaigns);

    void applyCouponDiscount(Long cartId, Coupon coupon);

    void deleteById(Long id);
}
