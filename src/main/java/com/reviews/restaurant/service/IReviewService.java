package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ReviewRequestDTO;
import com.reviews.restaurant.dto.ReviewResponseDTO;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IReviewService {

    ReviewResponseDTO addReview(ReviewRequestDTO review, List<MultipartFile> images);

    Review convertToEntity(ReviewRequestDTO review, Restaurant restaurant, User user);
}
