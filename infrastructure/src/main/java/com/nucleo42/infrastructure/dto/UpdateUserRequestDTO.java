package com.nucleo42.infrastructure.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequestDTO(
        @NotNull(message = "The 'id' field is required")
        @JsonProperty("user_id")
        UUID id,
        @NotBlank(message = "The 'firstName' field is required")
        @JsonProperty("first_name")
        String firstName,
        @NotBlank(message = "The 'lastName' field is required")
        @JsonProperty("last_name")
        String lastName,
        @NotBlank(message = "The 'email' field is required")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "The 'password' field is required")
        String password,
        String biography
        ){    
}
