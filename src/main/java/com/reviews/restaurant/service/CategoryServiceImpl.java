package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.CategoryDTO;
import com.reviews.restaurant.maps.IMapCategory;
import com.reviews.restaurant.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final IMapCategory mapCategory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, IMapCategory mapCategory) {
        this.categoryRepository = categoryRepository;
        this.mapCategory = mapCategory;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return mapCategory.mapCategoryList(categoryRepository.findAll());
    }
}
