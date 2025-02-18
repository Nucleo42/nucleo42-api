package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.application.gateway.FindAllProjectsGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.mapper.ProjectMapper;
import com.nucleo42.infrastruture.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectGateway implements AddProjectGateway, FindAllProjectsGateway {

    @Autowired
    private ProjectRepository projectEntityRepository;

    @Override
    public Boolean create(Project project) {
        projectEntityRepository.save(ProjectMapper.toEntity(project));
        return true;
    }
    
    @Override
    public List<Project> findAll() {
        return projectEntityRepository.findAll().stream().map(ProjectMapper::fromEntity).toList();
    }
}

