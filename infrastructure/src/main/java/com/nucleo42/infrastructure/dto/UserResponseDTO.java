package com.nucleo42.infrastructure.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nucleo42.entity.Skill;

public record UserResponseDTO(
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String email,
        String biography,
        List<Skill> skills) {
}
