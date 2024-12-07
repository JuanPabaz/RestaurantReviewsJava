package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantRequestDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface IRestaurantService {

    List<RestaurantResponseDTO> getAllRestaurants();

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurant) throws BadRequestException;
}
