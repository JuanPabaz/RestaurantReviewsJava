package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.FavoriteResponseDTO;

public interface IUserService {

    FavoriteResponseDTO addFavoriteRestaurant(Long restaurantId, Long userId);
}
