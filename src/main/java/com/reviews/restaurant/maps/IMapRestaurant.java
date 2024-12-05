package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.RestaurantDTO;
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
            @Mapping(source = "category.idCategory", target = "idCategory")
    })
    RestaurantDTO mapRestaurant(Restaurant restaurant);

    List<RestaurantDTO> mapRestaurantList(List<Restaurant> restaurantList);

}