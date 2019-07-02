package com.anilakdemir.ecommerce.service.impl;

import com.anilakdemir.ecommerce.model.ProductOrder;
import com.anilakdemir.ecommerce.repository.ProductOrderRepository;
import com.anilakdemir.ecommerce.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderServiceImpl  implements ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    @Autowired
    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public Optional<ProductOrder> findById(Long id) {
        return productOrderRepository.findById(id);
    }

    @Override
    public List<ProductOrder> findAll() {
        return productOrderRepository.findAll();
    }

    @Override
    public ProductOrder save(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    @Override
    public void deleteById(Long id) {
        productOrderRepository.deleteById(id);
    }
}
