package com.nucleo42.infrastructure.service;

import com.nucleo42.entity.Project;
import com.nucleo42.exception.RegisterDoesNotExistsException;
import com.nucleo42.infrastructure.entity.ProjectEntity;
import com.nucleo42.infrastructure.mapper.ProjectMapper;
import com.nucleo42.infrastructure.repository.ProjectRepository;
import com.nucleo42.usecase.findall.FindAllProjectsParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Nested
    class Create {

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

    }

    @Nested
    class FindAll {

        @Test
        @DisplayName("Should call ProjectRepository.findAll correctly")
        void test01() {
            try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
                mockedProjectMapper.when(() -> ProjectMapper.fromEntity(projectEntity)).thenReturn(project);

                projectGateway.findAll(new FindAllProjectsParams(null, null, null, null, null,  null));
                verify(projectRepository).findAll(any(Specification.class));
            }
        }

    }

    @Nested
    class FindById {

        @Test
        @DisplayName("Should call ProjectRepository.findById correctly")
        void test01() {
            UUID projectId = UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb");
            when(projectRepository.findById(projectId)).thenReturn(Optional.of(projectEntity));

            try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
                mockedProjectMapper.when(() -> ProjectMapper.fromEntity(projectEntity)).thenReturn(project);
                projectGateway.findById(projectId);
                verify(projectRepository).findById(projectId);
            }
        }

        @Test
        @DisplayName("Should return a Project when success")
        void test02() {
            UUID projectId = UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb");
            when(projectRepository.findById(projectId)).thenReturn(Optional.of(projectEntity));

            try (var mockedProjectMapper = mockStatic(ProjectMapper.class)){
                mockedProjectMapper.when(() -> ProjectMapper.fromEntity(projectEntity)).thenReturn(project);
                Project projectData = projectGateway.findById(projectId);

                assert projectData.equals(project);
            }
        }
    }

    @Nested
    class Remove {

        @Test
        @DisplayName("Should call ProjectRepository.remove correctly")
        void test01() {
            projectGateway.remove(project.getId());
            verify(projectRepository).deleteById(project.getId());
        }

        @Test
        @DisplayName("Should returns true on success")
        void test02() {
            Boolean result = projectGateway.remove(project.getId());
            assert result.equals(true);
        }

    }

    @Nested
    class Update {

        @Test
        @DisplayName("Should call ProjectRepository.save correctly when update")
        void test01() {
            try (var mockedProjectMapper = mockStatic(ProjectMapper.class)) {
                mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
                projectGateway.update(project);
                verify(projectRepository).save(projectEntity);
            }
        }

        @Test
        @DisplayName("Should returns true on success when update")
        void test02() {
            try (var mockedProjectMapper = mockStatic(ProjectMapper.class)) {
                mockedProjectMapper.when(() -> ProjectMapper.toEntity(project)).thenReturn(projectEntity);
                project.setId(UUID.fromString("167fa082-dca1-4c30-a136-356a5c57bacb"));

                Boolean result = projectGateway.update(project);
                System.out.println(result);
                assert result.equals(true);
            }
        }
    }

}