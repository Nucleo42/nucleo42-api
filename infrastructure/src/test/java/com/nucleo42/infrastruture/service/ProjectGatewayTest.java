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

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectGatewayTest {

    @InjectMocks
    private ProjectGateway projectGateway;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectEntity projectEntity;

    @Mock
    private Project project;

    @Test
    @DisplayName("Should call ProjectRepository.save correctly")
    void test01() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
            mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
            projectGateway.create(project);
            verify(projectRepository).save(projectEntity);
        }
    }

    @Test
    @DisplayName("Should returns true on success")
    void test02() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
            mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
            Boolean result = projectGateway.create(project);
            assert result.equals(true);
        }
    }

    @Test
    @DisplayName("Should call ProjectRepository.findAll correctly")
    void test03() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
            mockedProjectMapper.when(() -> ProjectMapper.fromEntity(projectEntity)).thenReturn(project);

            projectGateway.findAll();

            verify(projectRepository).findAll();
        }
    }

    @Test
    @DisplayName("Should call ProjectRepository.remove correctly")
    void test04() {
        projectGateway.remove(project.getId());
        verify(projectRepository).deleteById(project.getId());
    }
}