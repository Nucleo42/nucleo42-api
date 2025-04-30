package com.nucleo42.usecase;

import com.nucleo42.entity.Project;

import java.util.List;

public interface FindAllProjects {
    public List<Project> findAll(String name, Integer vacancies, List<Long> technologies, String memberName, Integer month, Integer year);
}
