package com.nucleo42.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.UpdateUserProfileSkillsCase;
import com.nucleo42.dto.request.UpdateSkillRequestDTO;
import com.nucleo42.entity.Skill;
import com.nucleo42.exception.SkillDoesNotExistException;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.exception.UserIdIsInvalidException;
import com.nucleo42.exception.UserIdIsNullException;
import com.nucleo42.mapper.SkillMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user/update/skills")
public class UpdateUserProfileSkillsController {
    @Autowired
    private UpdateUserProfileSkillsCase updateSkillsCase;

    @PutMapping
    public ResponseEntity<?> updateSkills(@RequestBody @Valid UpdateSkillRequestDTO skillRequest) {
        try {
            List<Skill> skills = SkillMapper.skillFromId(skillRequest.skills());
            updateSkillsCase.updateSkills(skills, skillRequest.userId());
        } catch (SkillDoesNotExistException | UserDoesNotExistException | UserIdIsNullException | UserIdIsInvalidException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Skills updated successufully");
    }

}
