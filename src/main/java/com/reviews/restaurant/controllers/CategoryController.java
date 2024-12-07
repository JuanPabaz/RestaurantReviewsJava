package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.CategoryDTO;
import com.reviews.restaurant.service.ICategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
