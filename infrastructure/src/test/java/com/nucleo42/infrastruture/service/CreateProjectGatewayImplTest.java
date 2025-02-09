package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.ICreateProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.entity.ProjectEntity;
import com.nucleo42.infrastruture.mapper.ProjectMapper;
import com.nucleo42.infrastruture.repository.IProjectEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProjectGatewayImplTest {

    @InjectMocks
    private CreateProjectGatewayImpl createProjectGateway;

    @Mock
    private IProjectEntityRepository projectEntityRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private ProjectEntity projectEntity;

    @Mock
    private Project project;

    @Test
    @DisplayName("Should call IProjectEntityRepository.save correctly")
    void test01() {
        when(projectMapper.toProjectEntity(project)).thenReturn(projectEntity);
        createProjectGateway.create(project);
        verify(projectEntityRepository).save(projectEntity);
    }
}