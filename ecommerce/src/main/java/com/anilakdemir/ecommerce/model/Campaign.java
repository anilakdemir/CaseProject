package com.anilakdemir.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Category category;

    private Double amount;

    private Integer minimunQuantity;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    public Campaign(Category category, Double amount, Integer minimunQuantity, DiscountType discountType) {
        this.category = category;
        this.amount = amount;
        this.minimunQuantity = minimunQuantity;
        this.discountType = discountType;
    }

    public Campaign() {}
}
