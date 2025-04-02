package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.*;
import com.reviews.restaurant.entities.Image;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.entities.User;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.IMapReview;
import com.reviews.restaurant.repositories.ReviewRepository;
import com.reviews.restaurant.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService{

    private final ReviewRepository reviewRepository;

    private final IRestaurantService restaurantService;

    private final UserRepository userRepository;

    private final IMapReview mapReview;

    private final IIMageService imageService;

    private final S3ServiceImpl s3ServiceImpl;

    public ReviewServiceImpl(ReviewRepository reviewRepository, IRestaurantService restaurantService, UserRepository userRepository, IMapReview mapReview, IIMageService imageService, S3ServiceImpl s3ServiceImpl) {
        this.reviewRepository = reviewRepository;
        this.restaurantService = restaurantService;
        this.userRepository = userRepository;
        this.mapReview = mapReview;
        this.imageService = imageService;
        this.s3ServiceImpl = s3ServiceImpl;
    }

    @Override
    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO, List<MultipartFile> images) {
        if (reviewRequestDTO.getIdUser() == null){
            throw new BadCreateRequest("La reseña debe tener un usuario asociado");
        }

        if (reviewRequestDTO.getIdRestaurant() == null){
            throw new BadCreateRequest("La reseña debe tener un restaurante asociado");
        }
        User user = userRepository.findById(reviewRequestDTO.getIdUser()).orElseThrow(() -> new BadCreateRequest("El usuario no existe"));
        Restaurant restaurant = restaurantService.getRestaurantById(reviewRequestDTO.getIdRestaurant()).orElseThrow(() -> new BadCreateRequest("El restaurant no existe"));

        createReviewValidations(reviewRequestDTO);

        Review review = convertToEntity(reviewRequestDTO,restaurant,user);
        review.updateTotalScore();
        ReviewResponseDTO reviewResponseDTO = mapReview.mapReview(reviewRepository.save(review));

        List<Image> imageResponse = s3ServiceImpl.saveImages(images, review, null);
        List<ImageRequestDTO> imageRequestDTOList = imageResponse
                .stream()
                .map(image -> {
                    ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
                    imageRequestDTO.setImage(image.getImage());
                    imageRequestDTO.setIdReview(review.getIdReview());
                    return imageRequestDTO;
                })
                .toList();

        List<ImageResponseDTO> imageResponseDTOList = imageService.saveImages(imageRequestDTOList);
        reviewResponseDTO.setImages(imageResponseDTOList);
        return reviewResponseDTO;
    }

    @Override
    public Review convertToEntity(ReviewRequestDTO reviewRequestDTO, Restaurant restaurant, User user) {
        return Review.builder()
                .comments(reviewRequestDTO.getComments())
                .drinks(reviewRequestDTO.getDrinks())
                .food(reviewRequestDTO.getFood())
                .service(reviewRequestDTO.getService())
                .menu(reviewRequestDTO.getMenu())
                .music(reviewRequestDTO.getMusic())
                .place(reviewRequestDTO.getPlace())
                .waitingTime(reviewRequestDTO.getWaitingTime())
                .ambient(reviewRequestDTO.getAmbient())
                .restaurant(restaurant)
                .title(reviewRequestDTO.getTitle())
                .user(user)
                .build();
    }

    @Override
    public Page<ReviewResponseDTO> listReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(pagedReview -> {
                    ReviewResponseDTO reviewResponse = mapReview.mapReview(pagedReview);
                    Restaurant restaurant = pagedReview.getRestaurant();
                    reviewResponse.setRestaurant(restaurantService.mapRestaurant(restaurant));
                    List<ImageResponseDTO> imageResponseDTOList = imageService.mapImageList(pagedReview.getReviewImages());
                    reviewResponse.setImages(imageResponseDTOList);
                    return reviewResponse;
                }
                );
    }

    @Override
    public Page<ReviewResponseDTO> filterReviews(Pageable pageable, String name) {
        return reviewRepository.findByRestaurantName(name, pageable)
                .map(pagedReview -> {
                    ReviewResponseDTO reviewResponse = mapReview.mapReview(pagedReview);
                    Restaurant restaurant = pagedReview.getRestaurant();
                    reviewResponse.setRestaurant(restaurantService.mapRestaurant(restaurant));
                    List<ImageResponseDTO> imageResponseDTOList = imageService.mapImageList(pagedReview.getReviewImages());
                    reviewResponse.setImages(imageResponseDTOList);
                    return reviewResponse;}
                );
    }

    private void createReviewValidations(ReviewRequestDTO reviewRequestDTO) {
        if (reviewRequestDTO.getTitle() == null){
            throw new BadCreateRequest("La reseña debe tener un titulo");
        }
        if (reviewRequestDTO.getService() < 0){
            throw new BadCreateRequest("La puntiacion del servicio no puede ser negativa");
        }
        if (reviewRequestDTO.getWaitingTime() < 0){
            throw new BadCreateRequest("La puntiacion del tiempo de espera no puede ser negativa");
        }
        if (reviewRequestDTO.getPlace() < 0){
            throw new BadCreateRequest("La puntiacion del lugar no puede ser negativa");
        }
        if (reviewRequestDTO.getMusic() < 0){
            throw new BadCreateRequest("La puntiacion de la musica no puede ser negativa");
        }
        if (reviewRequestDTO.getMenu() < 0){
            throw new BadCreateRequest("La puntiacion del menu no puede ser negativa");
        }
        if (reviewRequestDTO.getDrinks() < 0){
            throw new BadCreateRequest("La puntiacion de las bebidas no puede ser negativa");
        }
        if (reviewRequestDTO.getFood() < 0){
            throw new BadCreateRequest("La puntiacion de la comida no puede ser negativa");
        }

        if (reviewRequestDTO.getAmbient() < 0){
            throw new BadCreateRequest("La puntiacion de la ambientación no puede ser negativa");
        }
    }

}
