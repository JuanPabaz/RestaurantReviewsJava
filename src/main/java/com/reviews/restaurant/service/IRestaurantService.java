package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantDTO;

import java.util.List;

public interface IRestaurantService {

    List<RestaurantDTO> getAllRestaurants();
}
