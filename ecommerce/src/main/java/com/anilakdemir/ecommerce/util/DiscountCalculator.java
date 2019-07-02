package com.anilakdemir.ecommerce.util;

import com.anilakdemir.ecommerce.model.*;
import org.hibernate.mapping.Collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountCalculator {
    private static Map<Category, List<Campaign>> splitCampaignsByCategories(List<Campaign> campaigns) {
        return campaigns.stream().collect(Collectors.groupingBy(Campaign::getCategory));
    }

    private static Map<Category, List<ProductOrder>> splitOrdersByCategories(List<ProductOrder> orderList, Map<Category, List<Campaign>> campaignsByCategories) {
        Map<Category, List<ProductOrder>> ordersByCategories = new HashMap<>();
        for(Category category: campaignsByCategories.keySet()){
            List<ProductOrder> orders = orderList.stream().filter(order -> order.getProduct().getCategory().equals(category)).collect(Collectors.toList());
            ordersByCategories.put(category, orders);
        }
        return ordersByCategories;
    }

    private static Map<Campaign, Double> calculateDiscountsByCampaign(Map<Category, List<Campaign>> campaignsByCategories, Map<Category, List<ProductOrder>> ordersByCategories) {
        Map<Campaign, Double> discountsByCampaign = new HashMap<>();
        for(Map.Entry<Category, List<Campaign>> entry : campaignsByCategories.entrySet()) {
            List<ProductOrder> ordersByCategory = ordersByCategories.get(entry.getKey());
            Integer totalQuantityOfCategory = ordersByCategory.stream().mapToInt(order -> order.getQuantity()).sum();
            Double totalAmountOfOrdersByCategory = ordersByCategory.stream().mapToDouble(order -> (order.getProduct().getPrice() * order.getQuantity())).sum();

            for(Campaign campaign : entry.getValue()) {
                if (campaign.getMinimunQuantity() <= totalQuantityOfCategory) {
                    switch (campaign.getDiscountType()) {
                        case RATE: {
                            Double discount = totalAmountOfOrdersByCategory * (campaign.getAmount() / 100.0);
                            discountsByCampaign.put(campaign, discount);
                            break;
                        }
                        case AMOUNT: {
                            Double discount = campaign.getAmount();
                            discountsByCampaign.put(campaign, discount);
                            break;
                        }
                    }
                }
            }
        }
        return discountsByCampaign;
    }

    private static Pair<Campaign, Double> getMaximumDiscount(Map<Campaign, Double> discountsByCampaign) {
        Map.Entry<Campaign, Double> campaignWithMaximumDiscount = discountsByCampaign.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        Campaign maxDiscountCampaign = campaignWithMaximumDiscount.getKey();
        Double maxDiscount = campaignWithMaximumDiscount.getValue();
        return new Pair(maxDiscountCampaign, maxDiscount);
    }

    public  static Pair<Campaign, Double> calculateCampaignDiscount(List<ProductOrder> orderList, List<Campaign> campaigns) {
        Map<Category, List<Campaign>> campaignsByCategories = splitCampaignsByCategories(campaigns);;
        Map<Category, List<ProductOrder>> ordersByCategories = splitOrdersByCategories(orderList, campaignsByCategories);;
        Map<Campaign, Double> discountsByCampaign = calculateDiscountsByCampaign(campaignsByCategories, ordersByCategories);;

        return getMaximumDiscount(discountsByCampaign);
    }

    public  static Double calculateCouponDiscount(Double cartTotal, Double cartAmountAfterDiscounts, Coupon coupon) {
        Double couponDiscount = -1.0;
        if (cartTotal >= coupon.getMinimumAmount()) {

            switch (coupon.getDiscountType()) {
                case RATE: {
                    couponDiscount = cartAmountAfterDiscounts * (coupon.getDiscountAmount() / 100.0);
                    break;
                }
                case AMOUNT: {
                    couponDiscount = coupon.getDiscountAmount();
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return couponDiscount;
    }
}
