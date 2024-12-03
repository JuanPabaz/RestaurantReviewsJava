package com.reviews.restaurant.dto;

import com.reviews.restaurant.entities.Restaurant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {

    private Long idReview;
    private Double place;
    private Double food;
    private Double service;
    private Double drinks;
    private Double music;
    private Double menu;
    private Double waiting_time;
    private String comments;
    private Restaurant restaurant;

}
