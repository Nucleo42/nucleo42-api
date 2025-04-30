package com.nucleo42.infrastructure.mapper;

import java.util.ArrayList;
import java.util.List;

import com.nucleo42.infrastructure.dto.SkillRequestDTO;
import com.nucleo42.entity.Skill;
import com.nucleo42.infrastructure.entity.SkillEntity;
import com.nucleo42.infrastructure.entity.SkillEnum;

public class SkillMapper {

    public static List<SkillEntity> toSkillEntity(List<Skill> skills) {
        if (skills != null) {
            List<SkillEntity> skillEntities = new ArrayList<>();
            for (Skill s : skills) {
                SkillEntity skillEntity = new SkillEntity();
                skillEntity.setName(s.getName());
                skillEntities.add(skillEntity);
            }
            return skillEntities;
        }
        return null;
    }

    public static List<Skill> toSkill(List<SkillEntity> skillEntities) {
        if (skillEntities != null) {
            List<Skill> skills = new ArrayList<>();
            for (SkillEntity s : skillEntities) {
                skills.add(new Skill(s.getName()));
            }
            return skills;
        }
        return null;
    }

    public static List<Skill> skillFromId(List<SkillRequestDTO> skillsIds) {
        List<Skill> skills = new ArrayList<>();
        for (SkillRequestDTO skill : skillsIds) {
            Skill s = new Skill();
            s.setName(SkillEnum.fromId(skill.id()));
            skills.add(s);
        }
        return skills;
    }

}
