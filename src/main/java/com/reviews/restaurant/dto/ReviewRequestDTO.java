package com.reviews.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReviewRequestDTO {

    private Double place;
    private Double food;
    private Double service;
    private Double drinks;
    private Double music;
    private Double menu;
    private Double waitingTime;
    private Double ambient;
    private String comments;
    private Long idRestaurant;
    private Long idUser;

}
