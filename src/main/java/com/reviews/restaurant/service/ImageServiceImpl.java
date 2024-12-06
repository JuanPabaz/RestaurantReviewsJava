package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ImageRequestDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Image;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.maps.IMapImage;
import com.reviews.restaurant.repositories.ImageRepository;
import com.reviews.restaurant.repositories.RestaurantRepository;
import com.reviews.restaurant.repositories.ReviewRepository;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IIMageService{

    private final ImageRepository imageRepository;

    private final RestaurantRepository restaurantRepository;

    private final ReviewRepository reviewRepository;

    private final IMapImage mapImage;

    public ImageServiceImpl(ImageRepository imageRepository, RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, IMapImage mapImage) {
        this.imageRepository = imageRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.mapImage = mapImage;
    }

    @SneakyThrows
    @Override
    public List<ImageResponseDTO> saveImages(List<ImageRequestDTO> imagesRequestDTO) {
        List<Image> images = imagesRequestDTO.stream()
                .map(this::convertToEntity)
                .toList();
        return mapImage.mapImageList(imageRepository.saveAll(images));
    }

    private Image convertToEntity(ImageRequestDTO imageDTO) throws BadRequestException {
        Review review = new Review();
        if (imageDTO.getIdReview() != null) {
            review = reviewRepository.findById(imageDTO.getIdReview()).orElseThrow(() -> new BadRequestException("Reseña no encontrada"));
        }else {
            review = null;
        }
        Restaurant restaurant = new Restaurant();
        if (imageDTO.getIdRestaurant() != null) {
            restaurant = restaurantRepository.findById(imageDTO.getIdRestaurant()).orElseThrow(() -> new BadRequestException("Restaurant no encontrada"));
        }else {
            restaurant = null;
        }

        return Image.builder()
                .image(imageDTO.getImage())
                .restaurant(restaurant)
                .review(review)
                .build();
    }
}
