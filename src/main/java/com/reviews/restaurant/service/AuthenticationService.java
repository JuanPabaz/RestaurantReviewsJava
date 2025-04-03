package com.reviews.restaurant.service;

import com.reviews.restaurant.dto.RegisterRequestDTO;
import com.reviews.restaurant.dto.UsuarioResponseDTO;
import com.reviews.restaurant.entities.User;
import com.reviews.restaurant.enums.Role;
import com.reviews.restaurant.exceptions.BadUserCredentialsException;
import com.reviews.restaurant.exceptions.ObjectNotFoundException;
import com.reviews.restaurant.maps.UserMapper;
import com.reviews.restaurant.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public UsuarioResponseDTO saveUser(RegisterRequestDTO registerRequestDTO) throws BadUserCredentialsException {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
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
                .password(registerRequestDTO.getPassword())
                .role(registerRequestDTO.getRole())
                .fullName(registerRequestDTO.getFullName())
                .build();

        return UserMapper.mapUsuario(userRepository.save(user));
    }

    public String generateToken(String username) throws ObjectNotFoundException {
        return jwtService.generateToken(username);
    }

    public Map<String, Object> validateToken(String token){
        return jwtService.validateToken(token);
    }
}
