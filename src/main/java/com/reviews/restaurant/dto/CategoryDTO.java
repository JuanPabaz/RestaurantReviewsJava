package com.reviews.restaurant.dto;

import com.reviews.restaurant.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long idCategory;
    private String category;
    private String description;
    private Long idImage;
}
