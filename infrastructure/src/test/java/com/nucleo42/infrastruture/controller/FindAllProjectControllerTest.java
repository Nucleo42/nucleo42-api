package com.nucleo42.infrastruture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleo42.infrastruture.dto.CreateProjectRequestDTO;
import com.nucleo42.infrastruture.entity.ProjectEntity;
import com.nucleo42.infrastruture.repository.ProjectRepository;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class FindAllProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectEntityRepository;

    @Autowired
    ObjectMapper objectMapper;

    private CreateProjectRequestDTO createProjectRequestDTO;
    private List<ProjectEntity> projects;

    @BeforeEach
    void setup() {
        projectEntityRepository.deleteAll();
        createProjectRequestDTO = new CreateProjectRequestDTO("NucleoProject", "Its a Project", 12, "Make a project");
        projects = List.of(
                new ProjectEntity(
                        null,
                        "First project",
                        "Its a project",
                        12,
                        "Goal",
                        List.of(),
                        List.of(),
                        null,
                        null
                ),
                new ProjectEntity(
                        null,
                        "Second project",
                        "Its a project",
                        2,
                        "Goal",
                        List.of(),
                        List.of(),
                        null,
                        null
                )
        );
        projectEntityRepository.saveAll(projects);
    }

    @Test
    @DisplayName("Should return a empty project list")
    void test03() throws Exception {
        projectEntityRepository.deleteAll();
        projectEntityRepository.flush();
        mockMvc.perform(get("/project").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    @DisplayName("Should return all projects")
    void test04() throws Exception {
        mockMvc.perform(get("/project").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value(projects.getFirst().getName()))
                .andExpect(jsonPath("$[1].name").value(projects.get(1).getName()));
    }

    @Test
    @DisplayName("Should return all projects filtered by name")
    void test05() throws Exception {
        mockMvc.perform(get("/project")
                .param("name", "second").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value(projects.get(1).getName()));
    }
}