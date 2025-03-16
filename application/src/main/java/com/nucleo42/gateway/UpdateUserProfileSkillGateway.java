package com.nucleo42.gateway;

import java.util.List;

import com.nucleo42.entity.Skill;

public interface UpdateUserProfileSkillGateway {
    boolean updateSkills(List<Skill> skills, String userId);
}
