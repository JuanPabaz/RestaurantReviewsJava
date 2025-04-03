package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.UsuarioResponseDTO;
import com.reviews.restaurant.entities.User;

import java.util.List;

public class UserMapper {

    public static UsuarioResponseDTO mapUsuario(User user) {
        if (user == null) {
            return null;
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUser(user.getIdUser());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        dto.setUsername(user.getUsername());

        return dto;
    }

    public static List<UsuarioResponseDTO> mapUsuarioList(List<User> userList) {
        if (userList == null) {
            return null;
        }

        return userList.stream()
                .map(UserMapper::mapUsuario)
                .toList();
    }

}
