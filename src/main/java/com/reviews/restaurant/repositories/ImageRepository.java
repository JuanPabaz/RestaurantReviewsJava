package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
