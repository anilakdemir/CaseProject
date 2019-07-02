package com.anilakdemir.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<ProductOrder> productOrders;

    private Double totalAmount;

    private Double campaignDiscount;

    private Double couponDiscount;

    private Double amountAfterDiscounts;

    private Double deliveryCost;

    @ManyToOne
    @JoinColumn(name = "applied_campaign_id")
    private Campaign appliedCampaign;

    @ManyToOne
    @JoinColumn(name = "applied_coupon_id")
    private Coupon appliedCoupon;

    public ShoppingCart(List<ProductOrder> productOrders, Double totalAmount, Double campaignDiscount, Double couponDiscount, Double amountAfterDiscounts, Double deliveryCost, Campaign appliedCampaign, Coupon appliedCoupon) {
        this.productOrders = productOrders;
        this.totalAmount = totalAmount;
        this.campaignDiscount = campaignDiscount;
        this.couponDiscount = couponDiscount;
        this.amountAfterDiscounts = amountAfterDiscounts;
        this.deliveryCost = deliveryCost;
        this.appliedCampaign = appliedCampaign;
        this.appliedCoupon = appliedCoupon;
    }

    public ShoppingCart() {}
}
