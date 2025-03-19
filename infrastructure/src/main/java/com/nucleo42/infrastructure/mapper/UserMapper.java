package com.nucleo42.infrastructure.mapper;

import com.nucleo42.infrastructure.dto.UpdateUserRequestDTO;
import com.nucleo42.infrastructure.dto.UserResponse;

import java.util.List;
import java.util.UUID;

import com.nucleo42.entity.Skill;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserIdIsInvalidException;
import com.nucleo42.infrastructure.entity.SkillEntity;
import com.nucleo42.infrastructure.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        List<SkillEntity> skills = SkillMapper.toSkillEntity(user.getSkills());
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getAcceptTerms(),
                null,
                user.getBiography(),
                skills,
                null,
                null,
                null);
    }

    public static User toDomain(UserEntity userEntity) {
        List<Skill> skills = SkillMapper.toSkill(userEntity.getSkills());
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getBiography(),
                userEntity.getAceptTerms(),
                skills);
    }

    public static User toDomain(UpdateUserRequestDTO request) {
        UUID id = null;

        try {
            id = UUID.fromString(request.id());
        } catch (IllegalArgumentException e) {
            throw new UserIdIsInvalidException(request.id());
        }

        User user = new User();
        user.setId(id);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setBiography(request.biography());
        return user;
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail(), user.getBiography(),
                user.getSkills());
    }

    public static UserEntity updateUserEntity(UserEntity user, User userUpdate) {
        user.setId(userUpdate.getId());
        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(userUpdate.getPassword());
        user.setBiography(userUpdate.getBiography());
        return user;
    }

}
