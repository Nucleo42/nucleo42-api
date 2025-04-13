package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.UpdateProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.usecase.UpdateProject;

public class UpdateProjectImpl implements UpdateProject {

    private final UpdateProjectGateway updateProjectGateway;

    public UpdateProjectImpl(UpdateProjectGateway updateProjectGateway) {
        this.updateProjectGateway = updateProjectGateway;
    }

    @Override
    public String update(Project project) {
        this.updateProjectGateway.update(project);
        return "Project updated successfully";
    }
}
