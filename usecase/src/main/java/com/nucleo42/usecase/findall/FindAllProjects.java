package com.nucleo42.usecase.findall;

import com.nucleo42.entity.Project;

import java.util.List;

public interface FindAllProjects {
    public List<Project> findAll(FindAllProjectsParams params);
}
