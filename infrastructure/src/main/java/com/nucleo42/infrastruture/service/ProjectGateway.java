package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.application.gateway.FindAllProjectsGateway;
import com.nucleo42.application.gateway.RemoveProjectGateway;
import com.nucleo42.application.gateway.UpdateProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.entity.ProjectEntity;
import com.nucleo42.infrastruture.mapper.ProjectMapper;
import com.nucleo42.infrastruture.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectGateway implements AddProjectGateway, FindAllProjectsGateway, RemoveProjectGateway, UpdateProjectGateway {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Boolean create(Project project) {
        projectRepository.save(ProjectMapper.toEntity(project));
        return true;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll().stream().map(ProjectMapper::fromEntity).toList();
    }

    @Override
    public Boolean remove(UUID id) {
        projectRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean update(Project project) {
        if (project.getId() == null)
        {
            return false;
        }

        Optional<ProjectEntity> projectOptional = projectRepository.findById(project.getId());

        if (projectOptional.isPresent())
        {
            ProjectEntity projectData = projectOptional.get();

            projectData.setName(project.getName());
            projectData.setGoal(project.getGoal());
            projectData.setDescription(project.getDescription());
            projectData.setVacancies(project.getVacancies());

            projectRepository.save(projectData);
            return true;
        }
        return false;
    }
}

