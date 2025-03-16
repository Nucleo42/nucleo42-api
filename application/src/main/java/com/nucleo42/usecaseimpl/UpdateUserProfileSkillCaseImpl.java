package com.nucleo42.usecaseimpl;

import java.util.List;

import com.nucleo42.UpdateUserProfileSkillsCase;
import com.nucleo42.entity.Skill;
import com.nucleo42.gateway.UpdateUserProfileSkillGateway;

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
