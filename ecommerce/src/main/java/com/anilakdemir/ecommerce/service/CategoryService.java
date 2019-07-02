package com.anilakdemir.ecommerce.service;

import com.anilakdemir.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);

    List<Category> findAll();

    Category save(Category category);

    void deleteById(Long id);
}
