package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ImageRequestDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.dto.ReviewRequestDTO;
import com.reviews.restaurant.dto.ReviewResponseDTO;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.entities.User;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import com.reviews.restaurant.maps.IMapReview;
import com.reviews.restaurant.repositories.RestaurantRepository;
import com.reviews.restaurant.repositories.ReviewRepository;
import com.reviews.restaurant.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService{

    private final ReviewRepository reviewRepository;

    private final RestaurantRepository restaurantRepository;

    private final UsuarioRepository usuarioRepository;

    private final IMapReview mapReview;

    private final IIMageService imageService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, UsuarioRepository usuarioRepository, IMapReview mapReview, IIMageService imageService) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapReview = mapReview;
        this.imageService = imageService;
    }

    @Override
    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO) {
        if (reviewRequestDTO.getIdUser() == null){
            throw new BadCreateRequest("La reseña debe tener un usuario asociado");
        }

        if (reviewRequestDTO.getIdRestaurant() == null){
            throw new BadCreateRequest("La reseña debe tener un restaurante asociado");
        }
        User user = usuarioRepository.findById(reviewRequestDTO.getIdUser()).orElseThrow(() -> new BadCreateRequest("El usuario no existe"));
        Restaurant restaurant = restaurantRepository.findById(reviewRequestDTO.getIdRestaurant()).orElseThrow(() -> new BadCreateRequest("El restaurant no existe"));

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

        Review review = convertToEntity(reviewRequestDTO,restaurant,user);
        ReviewResponseDTO reviewResponseDTO = mapReview.mapReview(reviewRepository.save(review));
        List<ImageRequestDTO> imagesDTO = reviewRequestDTO.getImages().stream()
                .peek(image -> image.setIdReview(review.getIdReview()))
                .toList();

        List<ImageResponseDTO> imageResponseDTOList = imageService.saveImages(imagesDTO);
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
                .restaurant(restaurant)
                .user(user)
                .build();
    }


}
