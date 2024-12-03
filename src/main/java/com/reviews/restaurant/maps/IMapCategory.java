package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.CategoryDTO;
import com.reviews.restaurant.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapCategory {

    @Mappings({
            @Mapping(source = "idCategory",target = "idCategory"),
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "imageCategory", target = "imageCategory.idImage")
    })
    CategoryDTO mapCategory(Category category);

    List<CategoryDTO> mapCategoryList(List<Category> categoryList);
}
