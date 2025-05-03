package com.nucleo42.application.gateway;

import com.nucleo42.entity.Project;
import com.nucleo42.usecase.findall.FindAllProjectsParams;

import java.util.List;

public interface FindAllProjectsGateway {
    public List<Project> findAll(FindAllProjectsParams dto);
}
