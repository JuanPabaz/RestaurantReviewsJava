package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.UsuarioResponseDTO;
import com.reviews.restaurant.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapUsuario {

    @Mappings({
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "fullName",target = "fullName"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "username", target = "username")
    })
    UsuarioResponseDTO mapUsuario(User user);

    List<UsuarioResponseDTO> mapUsuarioList(List<User> userList);
}
