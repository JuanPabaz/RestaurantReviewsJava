package com.reviews.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ImageResponseDTO {

    private Long idImage;
    private String imageSrc;
    private String imageAlt;
}
