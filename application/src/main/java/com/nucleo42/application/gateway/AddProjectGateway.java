package com.nucleo42.application.gateway;

import com.nucleo42.entity.Project;

public interface AddProjectGateway {
    Boolean create(Project project);
}
