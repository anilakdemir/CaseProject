package com.anilakdemir.ecommerce.repository;

import com.anilakdemir.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Override
    List<ShoppingCart> findAll();

    @Override
    Optional<ShoppingCart> findById(Long id);

    @Override
    ShoppingCart save(ShoppingCart shoppingCart);

    @Override
    ShoppingCart getOne(Long id);

    @Override
    void deleteById(Long id);
}
