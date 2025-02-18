package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.exception.InternalServerErrorException;
import com.nucleo42.infrastruture.mapper.ProjectMapper;
import com.nucleo42.infrastruture.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectGateway implements AddProjectGateway {

    @Autowired
    private ProjectRepository projectEntityRepository;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Boolean create(Project project) {
        try {
            projectEntityRepository.save(projectMapper.toProjectEntity(project));
            return true;
        } catch (InternalServerErrorException e)
        {
            return false;
        }
    }
}

