package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.FavoriteResponseDTO;
import com.reviews.restaurant.dto.UserResponseDTO;
import com.reviews.restaurant.service.IUserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/favorites")
    public FavoriteResponseDTO addFavorites(@RequestParam Long idRestaurant, @RequestParam Integer idUser) {
        return userService.addFavoriteRestaurant(idRestaurant,idUser);
    }

    @GetMapping("/{idUser}")
    public UserResponseDTO getUserById(@PathVariable Integer idUser) {
        return userService.getUserById(idUser);
    }
}
