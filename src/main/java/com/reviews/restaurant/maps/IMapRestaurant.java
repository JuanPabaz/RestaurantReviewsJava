package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.RestaurantResponseDTO;
import com.reviews.restaurant.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapRestaurant {

    @Mappings({
            @Mapping(source = "idRestaurant",target = "idRestaurant"),
            @Mapping(source = "restuarantName", target = "restuarantName"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "phoneNumber", target = "phoneNumber"),
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "restuarantDescription", target = "restuarantDescription"),
            @Mapping(source = "avgPrices", target = "avgPrice"),
            @Mapping(source = "pageLink", target = "pageLink")
    })
    RestaurantResponseDTO mapRestaurant(Restaurant restaurant);

    List<RestaurantResponseDTO> mapRestaurantList(List<Restaurant> restaurantList);

}