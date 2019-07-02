package com.anilakdemir.ecommerce.service;

import com.anilakdemir.ecommerce.model.ProductOrder;

import java.util.List;
import java.util.Optional;

public interface ProductOrderService {
    Optional<ProductOrder> findById(Long id);

    List<ProductOrder> findAll();

    ProductOrder save(ProductOrder productOrder);

    void deleteById(Long id);
}
