package com.reviews.restaurant.dto;

import com.reviews.restaurant.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterRequestDTO {

    private String fullName;
    private String username;
    private String password;
    private Role role;
}
