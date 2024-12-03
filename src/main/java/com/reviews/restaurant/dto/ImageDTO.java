package com.reviews.restaurant.dto;

import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDTO {

    private Long idImage;
    private String image;
    private Restaurant restaurant;
    private Review review;
}
