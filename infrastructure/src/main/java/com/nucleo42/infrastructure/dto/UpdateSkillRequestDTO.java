package com.nucleo42.infrastructure.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateSkillRequestDTO(
        @NotNull(message = "The 'user_id' field is required")
        @JsonProperty("user_id")
        UUID id,
        @NotEmpty(message = "The 'skills' field is required")
        List<@Valid SkillRequestDTO> skills) {
}
