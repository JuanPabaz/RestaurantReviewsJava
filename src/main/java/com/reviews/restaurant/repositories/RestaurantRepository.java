package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT AVG(re.totalScore) FROM Restaurant AS res INNER JOIN Review AS re " +
            "ON res.idRestaurant = re.restaurant.idRestaurant WHERE res.idRestaurant = :idRestaurant")
    Double findAvgRatingByRestaurant(@Param("idRestaurant") Long idRestaurant);

    @Query("SELECT COUNT(re) FROM Restaurant AS res INNER JOIN Review AS re " +
            "ON res.idRestaurant = re.restaurant.idRestaurant WHERE res.idRestaurant = :idRestaurant")
    Integer findReviewCountByRestaurant(@Param("idRestaurant") Long idRestaurant);
}
