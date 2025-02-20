package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.RemoveProjectGateway;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RemoveProjectImplTest {

    @InjectMocks
    private RemoveProjectImpl removeProject;

    @Mock
    private RemoveProjectGateway removeProjectGateway;

    private Project project;

    @BeforeEach
    void setup()
    {
        project = new Project(
                UUID.randomUUID(),
                "NucleoProject",
                "Its a project",
                12,
                "Make a project",
                new ArrayList<>(),
                new ArrayList<>(),
                null,
                null);
        lenient().when(removeProjectGateway.remove(project.getId())).thenReturn(true);
    }

    @Test
    @DisplayName("Should call RemoveProjectGateway with correct value")
    void test01() {
        removeProject.remove(project.getId());
        verify(removeProjectGateway).remove(project.getId());
    }

    @Test
    @DisplayName("Should return success message when RemoveProjectGateway returns true")
    void test2() {
        when(removeProjectGateway.remove(project.getId())).thenReturn(true);
        assertEquals("Project successfully removed", removeProject.remove(project.getId()));
    }

}