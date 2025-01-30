package com.nucleo42.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nucleo42.dto.request.CreateUserRequest;
import com.nucleo42.dto.response.UserResponse;
import com.nucleo42.entity.Skill;
import com.nucleo42.entity.SkillEntity;
import com.nucleo42.entity.User;
import com.nucleo42.entity.UserEntity;

@Component
public class UserMapper {

    public User toUser(CreateUserRequest request) {
        return new User(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password(),
                request.biography());
    }

    public User toUser(UserEntity userEntity) {
        return new User(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getBiography());
    }

    public UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getBiography());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail(), user.getBiography(),
                user.getSkills());
    }

    public List<UserResponse> toResponse(List<User> users) {
        List<UserResponse> usersR = new ArrayList<>();

        for (User u : users) {
            usersR.add(
                    new UserResponse(u.getFirstName(), u.getLastName(), u.getEmail(), u.getBiography(), u.getSkills()));
        }
        return usersR;
    }

    public List<SkillEntity> toSkillEntity(List<Skill> skills) {
        List<SkillEntity> skillEntities = new ArrayList<>();
        for (Skill s : skills) {
            skillEntities.add(new SkillEntity(s.getName()));
        }
        return skillEntities;
    }

    public List<Skill> toSkill(List<SkillEntity> skillEntities) {
        List<Skill> skills = new ArrayList<>();
        for (SkillEntity s : skillEntities) {
            skills.add(new Skill(s.getName()));
        }
        return skills;
    }

}
