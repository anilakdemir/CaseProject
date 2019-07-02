package com.anilakdemir.ecommerce.controller;

import com.anilakdemir.ecommerce.model.Category;
import com.anilakdemir.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping(value = "/category/{id}",produces = "application/json")
    @ResponseBody
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
    @ResponseBody
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }
}
