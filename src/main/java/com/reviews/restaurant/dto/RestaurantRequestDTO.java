package com.reviews.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RestaurantRequestDTO {

    private String restuarantName;
    private String address;
    private String phoneNumber;
    private Long idCategory;
    private String pageLink;
}
