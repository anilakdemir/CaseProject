package com.anilakdemir.ecommerce.repository;

import com.anilakdemir.ecommerce.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Override
    List<ProductOrder> findAll();

    @Override
    Optional<ProductOrder> findById(Long id);

    @Override
    ProductOrder save(ProductOrder productOrder);

    @Override
    void deleteById(Long id);
}
