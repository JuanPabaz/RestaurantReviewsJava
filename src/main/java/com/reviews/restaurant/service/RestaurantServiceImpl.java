package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantRequestDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.entities.Category;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.IMapRestaurant;
import com.reviews.restaurant.repositories.CategoryRepository;
import com.reviews.restaurant.repositories.RestaurantRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final IMapRestaurant mapRestaurant;

    private final CategoryRepository categoryRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, IMapRestaurant mapRestaurant, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mapRestaurant = mapRestaurant;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return mapRestaurant.mapRestaurantList(restaurantRepository.findAll());
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

    private Restaurant convertToEntity(RestaurantRequestDTO restaurantRequestDTO, Category category) {
        return Restaurant.builder()
                .address(restaurantRequestDTO.getAddress())
                .phoneNumber(restaurantRequestDTO.getPhoneNumber())
                .category(category)
                .restuarantName(restaurantRequestDTO.getRestuarantName())
                .build();
    }
}
