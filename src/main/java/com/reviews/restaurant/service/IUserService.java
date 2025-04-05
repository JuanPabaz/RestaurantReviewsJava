package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.FavoriteResponseDTO;
import com.reviews.restaurant.dto.UserResponseDTO;

public interface IUserService {

    FavoriteResponseDTO addFavoriteRestaurant(Long restaurantId, Integer userId);

    UserResponseDTO getUserById(Integer userId);
}
