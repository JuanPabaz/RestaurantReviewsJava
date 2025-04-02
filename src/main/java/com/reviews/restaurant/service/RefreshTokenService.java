package com.reviews.restaurant.service;

import com.reviews.restaurant.entities.RefreshToken;
import com.reviews.restaurant.exceptions.ExpiredRefreshTokenException;
import com.reviews.restaurant.repositories.RefreshTokenRepository;
import com.reviews.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Value("${application.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpire;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenExpire))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new ExpiredRefreshTokenException("El token de actualización " + token.getToken() +  " esta vencido. Inicia sesión de nuevo");
        }
        return token;
    }



    public Optional<RefreshToken> findByUsername(String username){
        return refreshTokenRepository.findByUsername(username);
    }

    public void DeleteRefreshToken(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }
}
