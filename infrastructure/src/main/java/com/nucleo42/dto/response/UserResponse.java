package com.nucleo42.dto.response;

import java.util.List;

import com.nucleo42.entity.Skill;

public record UserResponse(
        String firstName,
        String lastName,
        String email,
        String biography,
        List<Skill> skills) {
}
