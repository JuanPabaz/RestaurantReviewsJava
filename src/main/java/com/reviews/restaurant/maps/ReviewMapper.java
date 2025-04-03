package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.dto.ReviewResponseDTO;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.service.IIMageService;
import com.reviews.restaurant.service.IRestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewMapper {

    private final IRestaurantService restaurantService;

    private final IIMageService imageService;

    public ReviewMapper(IRestaurantService restaurantService, IIMageService imageService) {
        this.restaurantService = restaurantService;
        this.imageService = imageService;
    }

    public ReviewResponseDTO mapReview(Review review) {
        if (review == null) {
            return null;
        }

        List<ImageResponseDTO> imageResponseDTOList = imageService.mapImageList(review.getReviewImages());


        ReviewResponseDTO reviewResponse = new ReviewResponseDTO();
        reviewResponse.setIdReview(review.getIdReview());
        reviewResponse.setTitle(review.getTitle());
        reviewResponse.setPlace(review.getPlace());
        reviewResponse.setRestaurant(restaurantService.mapRestaurant(review.getRestaurant()));
        reviewResponse.setFood(review.getFood());
        reviewResponse.setService(review.getService());
        reviewResponse.setDrinks(review.getDrinks());
        reviewResponse.setMusic(review.getMusic());
        reviewResponse.setMenu(review.getMenu());
        reviewResponse.setWaitingTime(review.getWaitingTime());
        reviewResponse.setTotalScore(review.getTotalScore());
        reviewResponse.setComments(review.getComments());
        reviewResponse.setImages(imageResponseDTOList);

        if (review.getUser() != null) {
            reviewResponse.setIdUser(review.getUser().getIdUser());
        }

        return reviewResponse;
    }

    public List<ReviewResponseDTO> mapReviewList(List<Review> reviewList) {
        if (reviewList == null) {
            return null;
        }

        return reviewList.stream()
                .map(this::mapReview)
                .toList();
    }

}
