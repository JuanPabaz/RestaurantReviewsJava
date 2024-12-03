package com.reviews.restaurant.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestaurantDTO {

    private Long idRestaurante;
    private String nombreRestaurante;
    private String direccion;
    private String telefono;
    private Long idCategoria;
    private List<Long> imagenList;
    private List<Long> resenaList;
}
