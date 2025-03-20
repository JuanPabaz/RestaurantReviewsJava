package com.reviews.restaurant.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviews.restaurant.dto.ReviewRequestDTO;
import com.reviews.restaurant.dto.ReviewResponseDTO;
import com.reviews.restaurant.service.IReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class ReviewController {

    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ReviewResponseDTO addReview(@RequestPart("review") String reviewJson,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewRequestDTO reviewRequestDTO = objectMapper.readValue(reviewJson, ReviewRequestDTO.class);
        return reviewService.addReview(reviewRequestDTO, images);
    }

    @GetMapping
    public Page<ReviewResponseDTO> getAllReviews(@RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return reviewService.listReviews(pageable);
    }

    @GetMapping("/filter-review")
    public Page<ReviewResponseDTO> filterReviews(@RequestParam Integer page, @RequestParam String title) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return reviewService.filterReviews(pageable, title);
    }

}
