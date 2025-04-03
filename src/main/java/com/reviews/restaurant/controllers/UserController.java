package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.FavoriteResponseDTO;
import com.reviews.restaurant.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/favorites")
    public FavoriteResponseDTO addFavorites(@RequestParam Long idRestaurant, @RequestParam Long idUser) {
        return userService.addFavoriteRestaurant(idRestaurant,idUser);
    }
}
