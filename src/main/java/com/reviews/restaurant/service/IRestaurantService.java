package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantRequestDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IRestaurantService {

    Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurant) throws BadRequestException;
}
