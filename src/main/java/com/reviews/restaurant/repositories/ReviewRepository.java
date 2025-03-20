package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review AS r INNER JOIN Restaurant AS res " +
            "ON r.restaurant.idRestaurant = res.idRestaurant " +
            "WHERE res.restuarantName ILIKE %:restaurantName%")
    Page<Review> findByRestaurantName(@Param("restaurantName") String restaurantName, Pageable pageable);
}
