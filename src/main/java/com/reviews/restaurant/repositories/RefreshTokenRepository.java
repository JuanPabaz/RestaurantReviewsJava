package com.reviews.restaurant.repositories;

import com.reviews.restaurant.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Query("SELECT r FROM RefreshToken r INNER JOIN User u ON r.user.idUser = u.idUser WHERE u.username = :username")
    Optional<RefreshToken> findByUsername(String username);

}
