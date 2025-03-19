package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ImageRequestDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Image;

import java.util.List;

public interface IIMageService {

    List<ImageResponseDTO> saveImages(List<ImageRequestDTO> images);

    ImageResponseDTO mapImageToResponseDTO(Image image);

    List<ImageResponseDTO> mapImageListToResponseDTO(List<Image> images);

}
