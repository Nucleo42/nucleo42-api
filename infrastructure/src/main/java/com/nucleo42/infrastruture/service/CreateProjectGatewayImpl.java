package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.ICreateProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.exception.InternalServerErrorException;
import com.nucleo42.infrastruture.mapper.ProjectMapper;
import com.nucleo42.infrastruture.repository.IProjectEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectGatewayImpl implements ICreateProjectGateway {

    @Autowired
    private IProjectEntityRepository projectEntityRepository;
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

