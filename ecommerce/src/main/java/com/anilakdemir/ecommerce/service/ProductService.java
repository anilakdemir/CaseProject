package com.anilakdemir.ecommerce.service;

import com.anilakdemir.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);
}
