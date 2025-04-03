package com.reviews.restaurant.service;

import com.reviews.restaurant.entities.Favorites;

public interface IUserService {

    Favorites addFavoriteRestaurant(Long restaurantId, Long userId);
}
