package com.reviews.restaurant.controllers;


import com.reviews.restaurant.dto.AuthRequestDTO;
import com.reviews.restaurant.dto.AuthResponseDTO;
import com.reviews.restaurant.dto.UsuarioResponseDTO;
import com.reviews.restaurant.entities.RefreshToken;
import com.reviews.restaurant.entities.Usuario;
import com.reviews.restaurant.exceptions.BadUserCredentialsException;
import com.reviews.restaurant.exceptions.ExpiredRefreshTokenException;
import com.reviews.restaurant.exceptions.ObjectNotFoundException;
import com.reviews.restaurant.repositories.UsuarioRepository;
import com.reviews.restaurant.service.AuthenticationService;
import com.reviews.restaurant.service.JwtServiceImpl;
import com.reviews.restaurant.service.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtService;

    private final UsuarioRepository usuarioRepository;

    public AuthenticationController(AuthenticationService authenticationService, RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager, JwtServiceImpl jwtService, UsuarioRepository usuarioRepository) {
        this.authenticationService = authenticationService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/register")
    public UsuarioResponseDTO addNewUser(@RequestBody Usuario usuario) throws Exception {
        return authenticationService.saveUser(usuario);
    }

    @PostMapping("/login")
    public AuthResponseDTO getToken(@RequestBody AuthRequestDTO authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            Optional<RefreshToken> refreshTokenOptional = refreshTokenService.findByUsername(authRequest.getUsername());
            refreshTokenOptional.ifPresent(refreshToken -> refreshTokenService.DeleteRefreshToken(refreshToken));
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            return AuthResponseDTO
                    .builder()
                    .accessToken(authenticationService.generateToken(authRequest.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .role(usuarioRepository.findRoleByUsername(authRequest.getUsername()))
                    .build();

        }catch (BadUserCredentialsException e){
            throw new BadUserCredentialsException(e.getMessage());
        } catch (Exception e){
            throw new BadUserCredentialsException("Usuario y/o contraseña incorrectas");
        }

    }

    @PostMapping("/refreshToken")
    public AuthResponseDTO refreshToken(@RequestBody AuthResponseDTO authResponseDTO){

        return refreshTokenService.findByToken(authResponseDTO.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsuario)
                .map(userCredential -> {
                    String accessToken = null;
                    try {
                        accessToken = jwtService.generateToken(userCredential.getUsername());
                    } catch (ObjectNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return AuthResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(authResponseDTO.getRefreshToken()).build();
                }).orElseThrow(() ->new ExpiredRefreshTokenException("El refresh token no se encuentra en la base de datos"));
    }

    @GetMapping("/validateToken/{token}")
    public Map<String, Object> validateToken(@PathVariable(name = "token") String token){
        return authenticationService.validateToken(token);
    }
}
