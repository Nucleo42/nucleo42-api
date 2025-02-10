package com.nucleo42.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank(message = "The 'firstName' field is required")
        String firstName,
        @NotBlank(message = "The 'lastName' field is required")
        String lastName,
        @NotBlank(message = "The 'email' field is required")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "The 'password' field is required")
        String password,
        @NotBlank(message = "The 'biography' field is required")
        String biography
        ){    
}
