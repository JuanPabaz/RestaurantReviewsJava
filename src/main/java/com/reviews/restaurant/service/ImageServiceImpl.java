package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ImageRequestDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Image;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.ImageMapper;
import com.reviews.restaurant.repositories.ImageRepository;
import com.reviews.restaurant.repositories.RestaurantRepository;
import com.reviews.restaurant.repositories.ReviewRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IIMageService{

    private final ImageRepository imageRepository;

    private final RestaurantRepository restaurantRepository;

    private final ReviewRepository reviewRepository;

    public ImageServiceImpl(ImageRepository imageRepository, RestaurantRepository restaurantRepository,
                            ReviewRepository reviewRepository) {
        this.imageRepository = imageRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    @SneakyThrows
    @Override
    public List<ImageResponseDTO> saveImages(List<ImageRequestDTO> imagesRequestDTO) {
        List<Image> images = imagesRequestDTO.stream()
                .map(this::convertToEntity)
                .toList();
        return ImageMapper.mapImageList(imageRepository.saveAll(images));
    }

    private Image convertToEntity(ImageRequestDTO imageDTO) throws BadCreateRequest {
        Review review = new Review();
        if (imageDTO.getIdReview() != null) {
            review = reviewRepository.findById(imageDTO.getIdReview()).orElseThrow(() -> new BadCreateRequest("Reseña no encontrada"));
        }else {
            review = null;
        }
        Restaurant restaurant = new Restaurant();
        if (imageDTO.getIdRestaurant() != null) {
            restaurant = restaurantRepository.findById(imageDTO.getIdRestaurant()).orElseThrow(() -> new BadCreateRequest("Restaurant no encontrada"));
        }else {
            restaurant = null;
        }

        return Image.builder()
                .image(imageDTO.getImage())
                .restaurant(restaurant)
                .review(review)
                .build();
    }

    public ImageResponseDTO mapImage(Image image) {
        return ImageMapper.mapImage(image);
    }

    public List<ImageResponseDTO> mapImageList(List<Image> images) {
        return ImageMapper.mapImageList(images);
    }
}
