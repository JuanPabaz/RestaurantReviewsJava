package com.reviews.restaurant.repositories;
import com.reviews.restaurant.entities.RestaurantFeatures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantFeaturesRepository extends JpaRepository<RestaurantFeatures, Long> {

    List<RestaurantFeatures> findAllByRestaurant_IdRestaurant(Long IdRestaurant);
}
