package com.anilakdemir.ecommerce.controller;

import com.anilakdemir.ecommerce.model.Coupon;
import com.anilakdemir.ecommerce.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @RequestMapping(value = "/coupon", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Coupon> getAllCoupons(){
        return couponService.findAll();
    }

    @GetMapping(value = "/coupon/{id}",produces = "application/json")
    @ResponseBody
    public Optional<Coupon> getCouponById(@PathVariable Long id) {
        return couponService.findById(id);
    }

    @PostMapping(value = "/coupon", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
    @ResponseBody
    public Coupon addCoupon(@RequestBody Coupon coupon) {
        return couponService.save(coupon);
    }
}
