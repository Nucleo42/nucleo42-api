package com.nucleo42.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleo42.infrastructure.entity.ProjectEntity;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class RemoveFindAllProjectControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    ObjectMapper objectMapper;

    private List<ProjectEntity> projects;

    @BeforeEach
    void setup() {
        projectRepository.deleteAll();
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
        projectRepository.saveAll(projects);
    }

    @Test
    @DisplayName("Should remove a project")
    void test01() throws Exception {
        mockMvc.perform(delete("/project/" + projects.getFirst().getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}