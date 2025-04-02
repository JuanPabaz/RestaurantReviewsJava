package com.reviews.restaurant.service;

import com.reviews.restaurant.entities.Favorites;
import org.apache.coyote.BadRequestException;

public interface IUserService {

    Favorites addFavoriteRestaurant(Long restaurantId, Long userId) throws BadRequestException;
}
