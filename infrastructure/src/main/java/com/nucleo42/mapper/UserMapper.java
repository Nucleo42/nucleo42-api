package com.nucleo42.mapper;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.nucleo42.dto.request.UpdateUserRequestDTO;
import com.nucleo42.dto.response.UserResponse;
import com.nucleo42.entity.Skill;
import com.nucleo42.entity.SkillEntity;
import com.nucleo42.entity.User;
import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserIdIsInvalidException;

@Component
public class UserMapper {

    public static User toUser(UserEntity userEntity) {
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

    public static User toUser(UpdateUserRequestDTO request) {
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

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setBiography(user.getBiography());
        userEntity.setAceptTerms(true);

        List<SkillEntity>skillEntity = SkillMapper.toSkillEntity(user.getSkills());
        userEntity.setSkills(skillEntity);

        return userEntity;
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
