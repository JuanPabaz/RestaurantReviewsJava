package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.service.IRestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class RestaurantController {

    private final IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public Page<RestaurantResponseDTO> getAllRestaurants(@RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        return restaurantService.getAllRestaurants(pageable);
    }


}
