package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
