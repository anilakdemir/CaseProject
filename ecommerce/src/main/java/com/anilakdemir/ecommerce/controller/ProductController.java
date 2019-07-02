package com.anilakdemir.ecommerce.controller;

import com.anilakdemir.ecommerce.model.Product;
import com.anilakdemir.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping(value = "/product/{id}",produces = "application/json")
    @ResponseBody
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}
