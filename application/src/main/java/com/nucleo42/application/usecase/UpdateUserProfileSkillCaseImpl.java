package com.nucleo42.application.usecase;

import java.util.List;

import com.nucleo42.application.gateway.UpdateUserProfileSkillGateway;
import com.nucleo42.entity.Skill;
import com.nucleo42.usecase.UpdateUserProfileSkillsCase;

public class UpdateUserProfileSkillCaseImpl implements UpdateUserProfileSkillsCase {
    private final UpdateUserProfileSkillGateway skillGateway;

    public UpdateUserProfileSkillCaseImpl(UpdateUserProfileSkillGateway skillGateway) {
        this.skillGateway = skillGateway;
    }

    @Override
    public boolean updateSkills(List<Skill> skills, String userId) {
        return skillGateway.updateSkills(skills, userId);
    }

}
