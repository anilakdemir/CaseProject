package com.anilakdemir.ecommerce.util;

import com.anilakdemir.ecommerce.model.*;

import java.util.stream.Collectors;

public class DeliveryCostCalculator {
    Double costPerDelivery;
    Double costPerProduct;
    Double fixedCost;

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public Double calculateFor(ShoppingCart cart) {
        Integer numberOfDeliveries = cart.getProductOrders().stream()
                .map(order -> order.getProduct().getCategory().getTitle())
                .collect(Collectors.toSet())
                .size();
        Integer numberOfProducts = cart.getProductOrders().stream().map(ProductOrder::getProduct).collect(Collectors.toList()).size();
        return (this.costPerDelivery * (double) numberOfDeliveries) + (this.costPerProduct * (double) numberOfProducts) + this.fixedCost;
    }
}
