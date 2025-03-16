package com.nucleo42.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UpdateSkillRequestDTO(
        @NotBlank(message = "The 'userId' field is required")
        String userId,
        @NotEmpty(message = "The 'skills' field is required")
        List<@Valid SkillRequestDTO> skills) {
}
