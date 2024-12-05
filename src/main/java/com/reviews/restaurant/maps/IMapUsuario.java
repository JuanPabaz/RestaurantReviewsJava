package com.reviews.restaurant.maps;

import com.reviews.restaurant.dto.UsuarioResponseDTO;
import com.reviews.restaurant.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapUsuario {

    @Mappings({
            @Mapping(source = "idUsuario", target = "idUsuario"),
            @Mapping(source = "nombreCompleto",target = "nombreCompleto"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "username", target = "username")
    })
    UsuarioResponseDTO mapUsuario(Usuario usuario);

    List<UsuarioResponseDTO> mapUsuarioList(List<Usuario> usuarioList);
}
