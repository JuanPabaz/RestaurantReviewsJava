package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Image;

import java.util.List;

public class ImageMapper {

    public static ImageResponseDTO mapImage(Image image) {
        if (image == null) {
            return null;
        }

        ImageResponseDTO dto = new ImageResponseDTO();
        dto.setIdImage(image.getIdImage());
        dto.setImageSrc(image.getImage());
        dto.setImageAlt(image.getImageAlt());

        return dto;
    }

    public static List<ImageResponseDTO> mapImageList(List<Image> imageList) {
        if (imageList == null) {
            return null;
        }

        return imageList.stream()
                .map(ImageMapper::mapImage)
                .toList();
    }
}
