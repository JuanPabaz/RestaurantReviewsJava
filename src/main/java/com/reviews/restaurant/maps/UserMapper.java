package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.UserResponseDTO;
import com.reviews.restaurant.entities.User;

import java.util.List;

public class UserMapper {

    public static UserResponseDTO mapUsuario(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setIdUser(user.getIdUser());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        dto.setUsername(user.getUsername());

        return dto;
    }

    public static List<UserResponseDTO> mapUsuarioList(List<User> userList) {
        if (userList == null) {
            return null;
        }

        return userList.stream()
                .map(UserMapper::mapUsuario)
                .toList();
    }

}
