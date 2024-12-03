package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantDTO;
import com.reviews.restaurant.maps.IMapRestaurant;
import com.reviews.restaurant.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final IMapRestaurant mapRestaurant;

    public RestaurantServiceImpl(IMapRestaurant mapRestaurant, RestaurantRepository restaurantRepository) {
        this.mapRestaurant = mapRestaurant;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return mapRestaurant.mapRestaurantList(restaurantRepository.findAll());
    }
}
