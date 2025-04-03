package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.FavoriteResponseDTO;
import com.reviews.restaurant.entities.Favorites;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.User;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.FavoriteMapper;
import com.reviews.restaurant.repositories.FavoriteRepository;
import com.reviews.restaurant.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final IRestaurantService restaurantService;

    private final FavoriteRepository favoriteRepository;

    private final FavoriteMapper favoriteMapper;

    public UserServiceImpl(UserRepository userRepository, IRestaurantService restaurantService, FavoriteRepository favoriteRepository, FavoriteMapper favoriteMapper) {
        this.userRepository = userRepository;
        this.restaurantService = restaurantService;
        this.favoriteRepository = favoriteRepository;
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public FavoriteResponseDTO addFavoriteRestaurant(Long restaurantId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadCreateRequest("Usuario no encontrado"));

        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId)
                .orElseThrow(() -> new BadCreateRequest("Restaurante no encontrado"));

        Favorites favorites = new Favorites();
        favorites.setRestaurant(restaurant);
        favorites.setUser(user);

        return favoriteMapper.mapFavorite(favoriteRepository.save(favorites));
    }
}
