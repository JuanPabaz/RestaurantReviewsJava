package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RestaurantRequestDTO;
import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.entities.Category;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.RestaurantMapper;
import com.reviews.restaurant.repositories.CategoryRepository;
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

    private final RestaurantMapper restaurantMapper;

    private final CategoryRepository categoryRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable)
                .map(restaurantMapper::mapRestaurant);
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
        return restaurantMapper.mapRestaurant(restaurantRepository.save(restaurant));
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public RestaurantResponseDTO mapRestaurant(Restaurant restaurant) {
        return restaurantMapper.mapRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponseDTO> mapRestaurantList(List<Restaurant> restaurantList) {
        return restaurantMapper.mapRestaurantList(restaurantList);
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
