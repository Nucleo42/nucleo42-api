package com.nucleo42.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleo42.infrastructure.dto.SkillRequestDTO;
import com.nucleo42.infrastructure.dto.UpdateSkillRequestDTO;
import com.nucleo42.infrastructure.entity.SkillEntity;
import com.nucleo42.infrastructure.entity.UserEntity;
import com.nucleo42.infrastructure.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UpdateUserProfileSkillControllerIT {
    @Autowired
    private UserRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final List<SkillRequestDTO> skillRequests = List.of(new SkillRequestDTO(1L), new SkillRequestDTO(2L),
            new SkillRequestDTO(3L));
    private final UpdateSkillRequestDTO userNotExistUpdateSkills = new UpdateSkillRequestDTO(
            "aaa3700f-f214-45a2-be5c-25a23c1b8f41c", skillRequests);

    private UserEntity user;
    private UpdateSkillRequestDTO userUpdateSkills; 

    @BeforeEach
    void setup() {
        repository.deleteAll();

        List<SkillEntity> skills = List.of(new SkillEntity(null, "Trabalho em equipe"),
                new SkillEntity(null, "Comunicação"));

        user = new UserEntity(null, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false, "Biografia", skills, null, null,null);

        user = repository.save(user);

        userUpdateSkills = new UpdateSkillRequestDTO(user.getId().toString(), skillRequests);
    }

    @Test
    @DisplayName("Should return code 200 if skills are upgraded")
    void updateUserProfileSkillCase01() throws JsonProcessingException, Exception {
        mockMvc.perform(put("/user/update/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdateSkills)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return code 400 if skills are not updated")
    void updateUserProfileSkillCase02() throws JsonProcessingException, Exception {
        mockMvc.perform(put("/user/update/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userNotExistUpdateSkills)))
                .andExpect(status().isBadRequest());
    }
}
