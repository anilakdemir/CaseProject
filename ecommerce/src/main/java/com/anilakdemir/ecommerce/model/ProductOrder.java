package com.anilakdemir.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double orderPrice;

    @ManyToOne
    private Product product;

    public ProductOrder() {};

    public ProductOrder(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
        if(quantity != null && product != null)
            this.orderPrice = (quantity * product.getPrice());
    }
}
