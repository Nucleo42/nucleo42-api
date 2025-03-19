package com.nucleo42.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDTO(
        @NotBlank(message = "The 'id' field is required")
        String id,
        @NotBlank(message = "The 'firstName' field is required")
        String firstName,
        @NotBlank(message = "The 'lastName' field is required")
        String lastName,
        @NotBlank(message = "The 'email' field is required")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "The 'password' field is required")
        String password,
        String biography
        ){    
}
