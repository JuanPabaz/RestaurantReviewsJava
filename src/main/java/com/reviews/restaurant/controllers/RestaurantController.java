package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.service.IRestaurantService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class RestaurantController {

    private final IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }


}
