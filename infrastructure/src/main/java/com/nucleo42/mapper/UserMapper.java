package com.nucleo42.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nucleo42.dto.request.SkillRequest;
import com.nucleo42.dto.request.UpdateUserRequest;
import com.nucleo42.dto.response.UserResponse;
import com.nucleo42.entity.Skill;
import com.nucleo42.entity.SkillEntity;
import com.nucleo42.dto.request.SkillEnum;
import com.nucleo42.entity.User;
import com.nucleo42.entity.UserEntity;

@Component
public class UserMapper {

    public static User toUser(UserEntity userEntity) {
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

    public static User toUser(UpdateUserRequest request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setBiography(request.biography());
        user.setSkills(skillFromId(request.skills()));
        return user;
    }

    public static UserEntity toEntity(User user) {
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

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail(), user.getBiography(),
                user.getSkills());
    }

    public static List<SkillEntity> toSkillEntity(List<Skill> skills) {
        List<SkillEntity> skillEntities = new ArrayList<>();
        for (Skill s : skills) {
            SkillEntity skillEntity = new SkillEntity();
            skillEntity.setName(s.getName());
            skillEntities.add(skillEntity);
        }
        return skillEntities;
    }

    private static List<Skill> toSkill(List<SkillEntity> skillEntities) {
        List<Skill> skills = new ArrayList<>();
        for (SkillEntity s : skillEntities) {
            skills.add(new Skill(s.getName()));
        }
        return skills;
    }
    private static List<Skill> skillFromId(List<SkillRequest> skillsIds){
        List<Skill> skills = new ArrayList<>();
        for(SkillRequest skill: skillsIds){
            Skill s = new Skill();
            s.setName(SkillEnum.fromId(skill.id()));
            skills.add(s);
        }
        return skills;
    }

}
