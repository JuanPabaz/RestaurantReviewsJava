package com.reviews.restaurant.dto;

import com.reviews.restaurant.entities.Image;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

    private Long idCategory;
    private String category;
    private String description;
    private Image imageCategory;
}
