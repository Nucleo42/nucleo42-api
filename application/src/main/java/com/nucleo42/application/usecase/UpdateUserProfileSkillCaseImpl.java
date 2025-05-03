package com.nucleo42.application.usecase;

import java.util.List;
import java.util.UUID;

import com.nucleo42.application.gateway.IUpdateUserProfileSkillGateway;
import com.nucleo42.entity.Skill;
import com.nucleo42.usecase.UpdateUserProfileSkillsCase;

public class UpdateUserProfileSkillCaseImpl implements UpdateUserProfileSkillsCase {
    private final IUpdateUserProfileSkillGateway skillGateway;

    public UpdateUserProfileSkillCaseImpl(IUpdateUserProfileSkillGateway skillGateway) {
        this.skillGateway = skillGateway;
    }

    @Override
    public boolean updateSkills(List<Skill> skills, UUID userId) {
        return skillGateway.updateSkills(skills, userId);
    }

}
