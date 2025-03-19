package com.nucleo42.infrastructure.dto;

import jakarta.validation.constraints.NotNull;

public record SkillRequestDTO(
        @NotNull(message = "The 'id' field is required")
        Long id) {
}
