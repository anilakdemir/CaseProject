package com.anilakdemir.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double discountAmount;

    private double minimumAmount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    public Coupon(double discountAmount, double minimumAmount, DiscountType discountType) {
        this.discountAmount = discountAmount;
        this.minimumAmount = minimumAmount;
        this.discountType = discountType;
    }

    public Coupon() {};
}
