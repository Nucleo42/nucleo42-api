package com.nucleo42.usecase;

import java.util.List;

import com.nucleo42.entity.Skill;

public interface UpdateUserProfileSkillsCase {
    boolean updateSkills(List<Skill> skills, String userId);
}
