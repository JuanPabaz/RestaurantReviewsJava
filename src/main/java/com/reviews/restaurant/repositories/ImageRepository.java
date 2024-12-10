package com.reviews.restaurant.repositories;

import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT m FROM Image AS m WHERE m.restaurant.idRestaurant = :idRestaurant")
    List<Image> findImagesByRestaurant(@Param("idRestaurant") Long idRestaurant);
}
