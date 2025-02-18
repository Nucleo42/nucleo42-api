package com.nucleo42.infrastruture.config;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.application.usecase.AddProjectImpl;
import com.nucleo42.infrastruture.service.ProjectGateway;
import com.nucleo42.usecase.AddProject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public AddProject createProjectUseCase(AddProjectGateway createProjectGateway) {
        return new AddProjectImpl(createProjectGateway);
    }

    @Bean
    public AddProjectGateway createProjectGateway() {
        return new ProjectGateway();
    }
}
