package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.usecase.AddProject;

public class AddProjectImpl implements AddProject {

    private final AddProjectGateway createProjectGateway;

    public AddProjectImpl(AddProjectGateway createProjectGateway)
    {
        this.createProjectGateway = createProjectGateway;
    }

    @Override
    public String create(Project project) {
        createProjectGateway.create(project);
        return "Project created successfully";
    }
}
