package com.nucleo42.usecase;

import java.util.List;
import java.util.UUID;

import com.nucleo42.entity.Skill;

public interface UpdateUserProfileSkillsCase {
    boolean updateSkills(List<Skill> skills, UUID userId);
}
