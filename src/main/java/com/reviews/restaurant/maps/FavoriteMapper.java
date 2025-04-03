package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.FavoriteResponseDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Favorites;
import com.reviews.restaurant.entities.Image;
import com.reviews.restaurant.service.IRestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteMapper {

    private final IRestaurantService restaurantService;

    public FavoriteMapper(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public FavoriteResponseDTO mapFavorite(Favorites favorites) {
        if (favorites == null) {
            return null;
        }

        FavoriteResponseDTO favoriteResponseDTO = new FavoriteResponseDTO();
        favoriteResponseDTO.setId(favorites.getIdFavorite());
        favoriteResponseDTO.setRestaurant(restaurantService.mapRestaurant(favorites.getRestaurant()));

        return favoriteResponseDTO;
    }

    public List<FavoriteResponseDTO> mapFavoriteList(List<Favorites> favoritesList) {
        if (favoritesList == null) {
            return null;
        }

        return favoritesList.stream()
                .map(this::mapFavorite)
                .toList();
    }
}
