package com.nucleo42.infrastructure.service;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastructure.entity.ProjectEntity;
import com.nucleo42.infrastructure.mapper.ProjectMapper;
import com.nucleo42.infrastructure.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
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

            projectGateway.findAll(null, null, null, null, null,  null);
            verify(projectRepository).findAll(any(Specification.class));
        }
    }

    @Test
    @DisplayName("Should call ProjectRepository.remove correctly")
    void test04() {
        projectGateway.remove(project.getId());
        verify(projectRepository).deleteById(project.getId());
    }

    @Test
    @DisplayName("Should returns true on success")
    void test05() {
        Boolean result = projectGateway.remove(project.getId());
        assert result.equals(true);
    }

    @Test
    @DisplayName("Should call ProjectRepository.save correctly when update")
    void test06() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)) {
            mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
            mockedProjectMapper.when(() -> project.getId()).thenReturn(UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb"));
            mockedProjectMapper.when(() -> projectRepository.findById(UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb"))).thenReturn(Optional.of(projectEntity));
            projectGateway.update(project);
            verify(projectRepository).save(projectEntity);
        }
    }

    @Test
    @DisplayName("Should returns true on success when update")
    void test07() {
        try (var mockedProjectMapper = mockStatic(ProjectMapper.class)) {
            mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
            mockedProjectMapper.when(() -> project.getId()).thenReturn(UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb"));
            mockedProjectMapper.when(() -> projectRepository.findById(UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb"))).thenReturn(Optional.of(projectEntity));
            Boolean result = projectGateway.update(project);
            assert result.equals(true);
        }
    }
}