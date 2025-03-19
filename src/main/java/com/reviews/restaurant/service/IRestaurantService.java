package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantRequestDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.entities.Restaurant;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IRestaurantService {

    Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurant) throws BadRequestException;

    Optional<Restaurant> getRestaurantById(Long id);

    RestaurantResponseDTO mapRestaurant(Restaurant restaurant);

    List<RestaurantResponseDTO> mapRestaurantList(List<Restaurant> restaurantList);
}
