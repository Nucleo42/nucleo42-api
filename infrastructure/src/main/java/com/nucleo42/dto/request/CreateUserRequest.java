package com.nucleo42.dto.request;

import java.util.List;

import com.nucleo42.entity.Skill;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String biography,
        List<Skill> skills) {
}