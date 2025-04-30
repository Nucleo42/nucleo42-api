package com.nucleo42.application.gateway;

import com.nucleo42.entity.Project;

import java.util.List;

public interface FindAllProjectsGateway {
    public List<Project> findAll(String name, Integer vacancies, List<Long> technology, String memberName, Integer month, Integer year);
}
