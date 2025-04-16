package com.nucleo42.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record SignUpRequestDTO(
        @Size(min = 3)
        @NotBlank
        @JsonProperty("first_name")
        String firstName,

        @Size(min = 3)
        @NotBlank
        @JsonProperty("last_name")
        String lastName,

        @Email
        @NotBlank
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
        @NotBlank
        String password,

        @NotNull
        @JsonProperty("accept_terms")
        Boolean acceptTerms
) {
}
