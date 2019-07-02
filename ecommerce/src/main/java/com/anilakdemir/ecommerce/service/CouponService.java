package com.anilakdemir.ecommerce.service;

import com.anilakdemir.ecommerce.model.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    Optional<Coupon> findById(Long id);

    List<Coupon> findAll();

    Coupon save(Coupon coupon);

    void deleteById(Long id);
}
