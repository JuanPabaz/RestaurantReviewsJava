package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.CategoryDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.RestaurantFeatures;
import com.reviews.restaurant.repositories.ImageRepository;
import com.reviews.restaurant.repositories.RestaurantRepository;
import com.reviews.restaurant.service.IIMageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantMapper {

    private final RestaurantRepository restaurantRepository;

    private final ImageRepository imageRepository;

    private final IIMageService imageService;

    public RestaurantMapper(RestaurantRepository restaurantRepository, ImageRepository imageRepository, IIMageService imageService) {
        this.restaurantRepository = restaurantRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    public RestaurantResponseDTO mapRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }

        List<ImageResponseDTO> images = imageService.mapImageList(imageRepository.findImagesByRestaurant(restaurant.getIdRestaurant()));
        Double avgRating = restaurantRepository.findAvgRatingByRestaurant(restaurant.getIdRestaurant());
        Integer reviewCount = restaurantRepository.findReviewCountByRestaurant(restaurant.getIdRestaurant());
        List<String> features = restaurant.getRestaurantFeaturesList()
                .stream()
                .map(RestaurantFeatures::getFeature)
                .toList();
        CategoryDTO categoryDTO = CategoryMapper.mapCategory(restaurant.getCategory());

        RestaurantResponseDTO restaurantResponseDTO = new RestaurantResponseDTO();
        restaurantResponseDTO.setIdRestaurant(restaurant.getIdRestaurant());
        restaurantResponseDTO.setRestuarantName(restaurant.getRestuarantName());
        restaurantResponseDTO.setAddress(restaurant.getAddress());
        restaurantResponseDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantResponseDTO.setCategory(categoryDTO);
        restaurantResponseDTO.setRestuarantDescription(restaurant.getRestuarantDescription());
        restaurantResponseDTO.setAvgPrice(restaurant.getAvgPrices());
        restaurantResponseDTO.setPageLink(restaurant.getPageLink());
        restaurantResponseDTO.setReviewCount(reviewCount);
        restaurantResponseDTO.setAvgRating(avgRating);
        restaurantResponseDTO.setImages(images);
        restaurantResponseDTO.setRestaurantFeatures(features);

        return restaurantResponseDTO;
    }

    public List<RestaurantResponseDTO> mapRestaurantList(List<Restaurant> restaurantList) {
        if (restaurantList == null) {
            return null;
        }

        return restaurantList.stream()
                .map(this::mapRestaurant)
                .toList();
    }
}
