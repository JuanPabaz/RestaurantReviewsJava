package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
