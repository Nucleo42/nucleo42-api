package com.nucleo42.dto.request;

import jakarta.validation.constraints.NotNull;

public record SkillRequestDTO(
        @NotNull(message = "The 'id' field is required")
        Long id) {
}
