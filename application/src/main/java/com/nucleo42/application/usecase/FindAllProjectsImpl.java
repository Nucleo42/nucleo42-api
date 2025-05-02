package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.FindAllProjectsGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.usecase.findall.FindAllProjects;
import com.nucleo42.usecase.findall.FindAllProjectsParams;

import java.util.List;

public class FindAllProjectsImpl implements FindAllProjects {

    private final FindAllProjectsGateway findAllProjectsGateway;

    public FindAllProjectsImpl(FindAllProjectsGateway findAllProjectsGateway) {
        this.findAllProjectsGateway = findAllProjectsGateway;
    }

    @Override
    public List<Project> findAll(FindAllProjectsParams params) {
        return findAllProjectsGateway.findAll(params);
    }
}
