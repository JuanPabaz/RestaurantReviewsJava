package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.User;
import com.reviews.restaurant.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :nombre")
    Role findRoleByUsername(String nombre);

}
