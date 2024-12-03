package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.ImageDTO;
import com.reviews.restaurant.entities.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapImage{

    @Mappings({
            @Mapping(source = "idImage",target = "idImage"),
            @Mapping(source = "image", target = "image"),
            @Mapping(source = "restaurant.id", target = "restaurant"),
            @Mapping(source = "review", target = "review"),
            @Mapping(source = "category", target = "category.idCategoria")
    })
    ImageDTO mapImage(Image image);

    List<ImageDTO> mapImageList(List<Image> imageList);
}
