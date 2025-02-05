package com.nucleo42.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nucleo42.dto.request.CreateUserRequest;
import com.nucleo42.dto.request.UpdateUserRequest;
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
                request.biography(),
                request.skills());
    }

    public User toUser(UserEntity userEntity) {
        List<Skill> skills = new ArrayList<>();
        skills = toSkill(userEntity.getSkills());
        return new User(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getBiography(),
                skills);
    }

    public User toUser(UpdateUserRequest userRequest) {
        return new User(
                userRequest.firstName(),
                userRequest.lastName(),
                userRequest.email(),
                userRequest.password(),
                userRequest.biography(),
                userRequest.skills());
    }

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        List<SkillEntity> skillEntity = new ArrayList<>();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setBiography(user.getBiography());

        skillEntity = toSkillEntity(user.getSkills());
        userEntity.setSkills(skillEntity);

        return userEntity;
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

    private List<SkillEntity> toSkillEntity(List<Skill> skills) {
        List<SkillEntity> skillEntities = new ArrayList<>();
        for (Skill s : skills) {
            SkillEntity skillEntity = new SkillEntity();
            skillEntity.setName(s.getName());
            skillEntities.add(skillEntity);
        }
        return skillEntities;
    }

    private List<Skill> toSkill(List<SkillEntity> skillEntities) {
        List<Skill> skills = new ArrayList<>();
        for (SkillEntity s : skillEntities) {
            skills.add(new Skill(s.getName()));
        }
        return skills;
    }

}
