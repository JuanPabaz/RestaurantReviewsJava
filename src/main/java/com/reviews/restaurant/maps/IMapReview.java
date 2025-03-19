package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.ReviewResponseDTO;
import com.reviews.restaurant.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapReview {

    @Mappings({
            @Mapping(source = "idReview", target = "idReview"),
            @Mapping(source = "place",target = "place"),
            @Mapping(source = "food", target = "food"),
            @Mapping(source = "service", target = "service"),
            @Mapping(source = "drinks", target = "drinks"),
            @Mapping(source = "music", target = "music"),
            @Mapping(source = "menu", target = "menu"),
            @Mapping(source = "waitingTime", target = "waitingTime"),
            @Mapping(source = "totalScore", target = "totalScore"),
            @Mapping(source = "comments", target = "comments"),
            @Mapping(source = "user.idUser", target = "idUser"),
    })
    ReviewResponseDTO mapReview(Review review);

    List<ReviewResponseDTO> mapReviewList(List<Review> reviewList);
}
