package com.reviews.restaurant.controllers;

import com.reviews.restaurant.dto.ReviewRequestDTO;
import com.reviews.restaurant.dto.ReviewResponseDTO;
import com.reviews.restaurant.service.IReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class ReviewController {

    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ReviewResponseDTO addReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        return reviewService.addReview(reviewRequestDTO);
    }
}
