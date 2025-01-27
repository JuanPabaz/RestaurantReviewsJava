package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RegisterRequestDTO;
import com.reviews.restaurant.dto.UsuarioResponseDTO;
import com.reviews.restaurant.entities.User;
import com.reviews.restaurant.enums.Role;
import com.reviews.restaurant.exceptions.BadUserCredentialsException;
import com.reviews.restaurant.exceptions.ObjectNotFoundException;
import com.reviews.restaurant.maps.IMapUsuario;
import com.reviews.restaurant.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtService;

    private final IMapUsuario mapUsuario;

    public AuthenticationService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtServiceImpl jwtService, IMapUsuario mapUsuario) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.mapUsuario = mapUsuario;
    }


    public UsuarioResponseDTO saveUser(RegisterRequestDTO registerRequestDTO) throws BadUserCredentialsException {
        if (usuarioRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new BadUserCredentialsException("Ya existe un usuario con este correo: "+ registerRequestDTO.getUsername() + ".");
        }

        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d/*\\-_.º!?¿'¡#!$%&]{6,}$";
        if (!registerRequestDTO.getPassword().matches(passwordRegex)){
            throw new BadUserCredentialsException("La contraseña debe tener al menos 6 caracteres y contener al menos una letra y un número.");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!registerRequestDTO.getUsername().matches(emailRegex)){
            throw new BadUserCredentialsException("El correo no es valido.");
        }

        registerRequestDTO.setRole(Role.ADMIN);
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        User user = User.builder()
                .username(registerRequestDTO.getUsername())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .role(registerRequestDTO.getRole())
                .fullName(registerRequestDTO.getFullName())
                .build();

        return mapUsuario.mapUsuario(usuarioRepository.save(user));
    }

    public String generateToken(String username) throws ObjectNotFoundException {
        return jwtService.generateToken(username);
    }

    public Map<String, Object> validateToken(String token){
        return jwtService.validateToken(token);
    }
}
