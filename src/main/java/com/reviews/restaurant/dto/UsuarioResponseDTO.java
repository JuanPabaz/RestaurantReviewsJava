package com.reviews.restaurant.dto;

import com.reviews.restaurant.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Integer idUser;
    private String nombreCompleto;
    private Role role;
    private String username;

}
