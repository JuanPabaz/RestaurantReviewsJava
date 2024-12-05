package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.Usuario;
import com.reviews.restaurant.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u.role FROM Usuario u WHERE u.username = :nombre")
    Role findRoleByUsername(String nombre);

}
