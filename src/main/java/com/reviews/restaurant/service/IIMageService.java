package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.ImageRequestDTO;
import com.reviews.restaurant.dto.ImageResponseDTO;

import java.util.List;

public interface IIMageService {

    List<ImageResponseDTO> saveImages(List<ImageRequestDTO> images);

}
