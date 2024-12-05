package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.RestaurantDTO;
import com.reviews.restaurant.service.IRestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestauranteController {

    private final IRestaurantService restaurantService;

    public RestauranteController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }


}
