package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.CategoryDTO;
import com.reviews.restaurant.maps.CategoryMapper;
import com.reviews.restaurant.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return CategoryMapper.mapCategoryList(categoryRepository.findAll());
    }
}
