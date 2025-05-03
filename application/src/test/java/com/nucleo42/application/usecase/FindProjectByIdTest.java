package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.FindProjectByIdGateway;
import com.nucleo42.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindProjectByIdTest {

    @InjectMocks
    private FindProjectByIdImpl findProjectById;

    @Mock
    private FindProjectByIdGateway findProjectByIdGateway;

    private Project project;

    private UUID projectId;

    @BeforeEach
    void setup()
    {
        projectId = UUID.randomUUID();
        project = new Project(
                projectId,
                "NucleoProject",
                "Its a project",
                12,
                "Make a project",
                new ArrayList<>(),
                new ArrayList<>(),
                null,
                null);
        lenient().when(findProjectByIdGateway.findById(projectId)).thenReturn(project);
    }

    @Test
    @DisplayName("Should call FindProjectByIdGateway with correct value")
    void test01() {
        findProjectById.findById(projectId);
        verify(findProjectByIdGateway).findById(projectId);
    }

    @Test
    @DisplayName("Should return the project when FindProjectByIdGateway is called")
    void test2() {
        when(findProjectByIdGateway.findById(projectId)).thenReturn(project);
        assertEquals(project, findProjectById.findById(projectId));
    }

    @Test
    @DisplayName("Should return null when project no exists")
    void test03() {
        UUID uuid = UUID.randomUUID();
        when(findProjectByIdGateway.findById(uuid)).thenReturn(null);
        assertNull(findProjectById.findById(uuid));
    }

}
