package com.nucleo42.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public record SkillRequestDTO(
        @NotNull(message = "The 'id' field is required")
        @JsonProperty("skill_id")
        Long id) {
}
