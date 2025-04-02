package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.dto.RestaurantRequestDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.entities.Category;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.RestaurantFeatures;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.IMapRestaurant;
import com.reviews.restaurant.repositories.CategoryRepository;
import com.reviews.restaurant.repositories.ImageRepository;
import com.reviews.restaurant.repositories.RestaurantFeaturesRepository;
import com.reviews.restaurant.repositories.RestaurantRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final IMapRestaurant mapRestaurant;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final IIMageService imageService;
    private final RestaurantFeaturesRepository restaurantFeaturesRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, IMapRestaurant mapRestaurant,
                                 CategoryRepository categoryRepository, ImageRepository imageRepository,
                                 IIMageService imageService, RestaurantFeaturesRepository restaurantFeaturesRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mapRestaurant = mapRestaurant;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.restaurantFeaturesRepository = restaurantFeaturesRepository;
    }

    @Override
    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable)
                .map(restaurant -> {
                    RestaurantResponseDTO restaurantResponseDTO = mapRestaurant.mapRestaurant(restaurant);
                    List<ImageResponseDTO> images = imageService.mapImageList(imageRepository.findImagesByRestaurant(restaurant.getIdRestaurant()));
                    Double avgRating = restaurantRepository.findAvgRatingByRestaurant(restaurant.getIdRestaurant());
                    Integer reviewCount = restaurantRepository.findReviewCountByRestaurant(restaurant.getIdRestaurant());
                    List<String> features = findRestaurantFeaturesByRestaurantId(restaurant.getIdRestaurant());
                    restaurantResponseDTO.setReviewCount(reviewCount);
                    restaurantResponseDTO.setAvgRating(avgRating);
                    restaurantResponseDTO.setImages(images);
                    restaurantResponseDTO.setRestaurantFeatures(features);
                 return restaurantResponseDTO;
                });
    }

    @Override
    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurantRequestDTO) throws BadRequestException {
        Category category = categoryRepository.findById(restaurantRequestDTO.getIdCategory()).orElseThrow(() -> new BadCreateRequest("La categoria no existe"));
        if (restaurantRequestDTO.getRestuarantName().isEmpty()) {
            throw new BadRequestException("El nombre del restaurante no puede estar vacio");
        }
        if (restaurantRequestDTO.getPhoneNumber().isEmpty()) {
            throw new BadRequestException("El numero de telefono no puede estar vacio");
        }
        if (restaurantRequestDTO.getAddress().isEmpty()) {
            throw new BadRequestException("La direccion del restaurante no puede estar vacia");
        }
        Restaurant restaurant = convertToEntity(restaurantRequestDTO, category);
        return mapRestaurant.mapRestaurant(restaurantRepository.save(restaurant));
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public RestaurantResponseDTO mapRestaurant(Restaurant restaurant) {
        return mapRestaurant.mapRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponseDTO> mapRestaurantList(List<Restaurant> restaurantList) {
        return mapRestaurant.mapRestaurantList(restaurantList);
    }

    @Override
    public List<String> findRestaurantFeaturesByRestaurantId(Long restaurantId) {
        return restaurantFeaturesRepository.findAllByRestaurant_IdRestaurant(restaurantId)
                .stream()
                .map(RestaurantFeatures::getFeature)
                .toList();
    }

    private Restaurant convertToEntity(RestaurantRequestDTO restaurantRequestDTO, Category category) {
        return Restaurant.builder()
                .address(restaurantRequestDTO.getAddress())
                .phoneNumber(restaurantRequestDTO.getPhoneNumber())
                .category(category)
                .restuarantName(restaurantRequestDTO.getRestuarantName())
                .build();
    }
}
