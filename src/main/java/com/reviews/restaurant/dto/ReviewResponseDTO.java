package com.reviews.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long idReview;
    private Double place;
    private Double food;
    private Double service;
    private Double drinks;
    private Double music;
    private Double menu;
    private Double waitingTime;
    private String comments;
    private Long idRestaurant;
    private Long idUser;
    private List<ImageResponseDTO> images;

}
