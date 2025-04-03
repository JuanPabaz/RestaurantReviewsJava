package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.CategoryDTO;
import com.reviews.restaurant.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO mapCategory(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setIdCategory(category.getIdCategory());
        dto.setCategory(category.getCategory());
        dto.setDescription(category.getDescription());

        if (category.getImageCategory() != null) {
            dto.setIdImage(category.getImageCategory().getIdImage());
        }

        return dto;
    }

    public static List<CategoryDTO> mapCategoryList(List<Category> categoryList) {
        if (categoryList == null) {
            return null;
        }

        return categoryList.stream()
                .map(CategoryMapper::mapCategory)
                .toList();
    }
}
