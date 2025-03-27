package com.reviews.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RestaurantResponseDTO {

    private Long idRestaurant;
    private String restuarantName;
    private String address;
    private String phoneNumber;
    private CategoryDTO category;
    private Double avgPrice;
    private String restuarantDescription;
    private String pageLink;
    private List<ImageResponseDTO> images;
}
