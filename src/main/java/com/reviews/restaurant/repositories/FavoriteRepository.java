package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorites, Long> {
}
