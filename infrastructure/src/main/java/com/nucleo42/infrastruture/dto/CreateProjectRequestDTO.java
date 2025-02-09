package com.nucleo42.infrastruture.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProjectRequestDTO (
        @Size(min = 3)
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        @Min(0)
        Integer vacancies,
        @NotBlank
        String goal
){
}
