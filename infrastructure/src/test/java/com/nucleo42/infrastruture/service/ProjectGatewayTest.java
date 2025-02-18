package com.nucleo42.infrastruture.service;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.entity.ProjectEntity;
import com.nucleo42.infrastruture.mapper.ProjectMapper;
import com.nucleo42.infrastruture.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectGatewayTest {

    @InjectMocks
    private ProjectGateway createProjectGateway;

    @Mock
    private ProjectRepository projectEntityRepository;

    @Mock
    private ProjectEntity projectEntity;

    @Mock
    private Project project;

    @Test
    @DisplayName("Should call IProjectEntityRepository.save correctly")
    void test01() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
            mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
            createProjectGateway.create(project);
            verify(projectEntityRepository).save(projectEntity);
        }
    }

    @Test
    @DisplayName("Should returns true on success")
    void test02() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
            mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
            Boolean result = createProjectGateway.create(project);
            assert result.equals(true);
        }
    }
}