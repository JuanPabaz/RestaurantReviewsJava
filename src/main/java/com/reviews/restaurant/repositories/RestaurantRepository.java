package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Image;
import com.reviews.restaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
