package com.nucleo42.infrastructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.infrastructure.dto.UpdateSkillRequestDTO;
import com.nucleo42.entity.Skill;
import com.nucleo42.infrastructure.mapper.SkillMapper;
import com.nucleo42.usecase.UpdateUserProfileSkillsCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user/update/skills")
public class UpdateUserProfileSkillsController {
    @Autowired
    private UpdateUserProfileSkillsCase updateSkillsCase;

    @PutMapping
    public ResponseEntity<?> updateSkills(@RequestBody @Valid UpdateSkillRequestDTO skillRequest) {
        List<Skill> skills = SkillMapper.skillFromId(skillRequest.skills());
        updateSkillsCase.updateSkills(skills, skillRequest.id());
        return ResponseEntity.ok().body("Skills updated successufully");
    }

}
