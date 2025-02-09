package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.ICreateProjectGateway;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateProjectUseCaseImplTest {

    @InjectMocks
    private CreateProjectUseCaseImpl createProjectUseCase;

    @Mock
    private ICreateProjectGateway createProjectGateway;

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
        lenient().when(createProjectGateway.create(project)).thenReturn(true);
    }

    @Test
    @DisplayName("Should call ICreateProjectGateway with correct value")
    void test01() {
        createProjectUseCase.create(project);
        verify(createProjectGateway).create(project);
    }
}