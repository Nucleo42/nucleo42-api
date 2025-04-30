package com.nucleo42.application.gateway;

import java.util.List;

import com.nucleo42.entity.Skill;

public interface IUpdateUserProfileSkillGateway {
    boolean updateSkills(List<Skill> skills, String userId);
}
