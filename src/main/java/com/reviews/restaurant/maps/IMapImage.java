package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.ImageResponseDTO;
import com.reviews.restaurant.entities.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapImage{

    @Mappings({
            @Mapping(source = "idImage",target = "idImage"),
            @Mapping(source = "image", target = "imageSrc"),
            @Mapping(source = "imageAlt", target = "imageAlt")
    })
    ImageResponseDTO mapImage(Image image);

    List<ImageResponseDTO> mapImageList(List<Image> imageList);
}