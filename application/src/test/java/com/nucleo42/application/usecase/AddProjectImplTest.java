package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddProjectImplTest {

    @InjectMocks
    private AddProjectImpl addProjectImpl;

    @Mock
    private AddProjectGateway addProjectGateway;

    private Project project;

    @BeforeEach
    void setup()
    {
        project = new Project(
                "NucleoProject",
                "Its a project",
                12,
                "Make a project",
                new ArrayList<>(),
                new ArrayList<>());
        lenient().when(addProjectGateway.create(project)).thenReturn(true);
    }

    @Test
    @DisplayName("Should call AddProjectGateway with correct value")
    void test01() {
        addProjectImpl.create(project);
        verify(addProjectGateway).create(project);
    }

    @Test
    @DisplayName("Should return success message when AddProjectGateway returns true")
    void test3() {
        when(addProjectGateway.create(project)).thenReturn(true);
        assertEquals("Project created successfully", addProjectImpl.create(project));
    }
}