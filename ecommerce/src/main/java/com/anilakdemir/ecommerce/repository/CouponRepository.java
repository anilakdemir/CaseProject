package com.anilakdemir.ecommerce.repository;

import com.anilakdemir.ecommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Override
    List<Coupon> findAll();

    @Override
    Optional<Coupon> findById(Long id);

    @Override
    Coupon save(Coupon coupon);

    @Override
    void deleteById(Long id);
}
