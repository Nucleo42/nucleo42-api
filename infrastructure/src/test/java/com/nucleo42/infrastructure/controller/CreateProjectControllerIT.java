package com.nucleo42.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleo42.infrastructure.dto.CreateProjectRequestDTO;
import com.nucleo42.infrastructure.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class CreateProjectControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectEntityRepository;

    @Autowired
    ObjectMapper objectMapper;

    private CreateProjectRequestDTO createProjectRequestDTO;

    @BeforeEach
    void setup() {
        projectEntityRepository.deleteAll();
        createProjectRequestDTO = new CreateProjectRequestDTO("NucleoProject", "Its a Project", 12, "Make a project");
    }

    @Test
    @DisplayName("Should create a project and return status 201 on success")
    void test01() throws Exception {
        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProjectRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return 422 when all request is invalid")
    void test02() throws Exception {
        createProjectRequestDTO = new CreateProjectRequestDTO("", "", 0, "");
        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProjectRequestDTO)))
                .andExpect(status().isUnprocessableEntity());
    }
}