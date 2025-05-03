package com.nucleo42.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProjectRequestDTO (
        @Size(min = 3, message = "The name must have at least 3 characters")
        @NotBlank(message = "Project's name is required")
        String name,

        @NotBlank(message = "Project's description is requried")
        String description,

        @NotNull(message = "Project's vacancies are required")
        @Min(value = 0, message = "The total number of vacancies must be a natural number")
        Integer vacancies,

        @NotBlank(message = "Project's goal is required")
        String goal
){
}
