package com.nucleo42.infrastruture.config;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.application.gateway.FindAllProjectsGateway;
import com.nucleo42.application.gateway.RemoveProjectGateway;
import com.nucleo42.application.usecase.AddProjectImpl;
import com.nucleo42.application.usecase.FindAllProjectsImpl;
import com.nucleo42.application.usecase.RemoveProjectImpl;
import com.nucleo42.infrastruture.service.ProjectGateway;
import com.nucleo42.usecase.AddProject;
import com.nucleo42.usecase.FindAllProjects;
import com.nucleo42.usecase.RemoveProject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ProjectGateway projectGateway()
    {
        return new ProjectGateway();
    }

    @Bean
    public AddProject createProjectUseCase(AddProjectGateway createProjectGateway) {
        return new AddProjectImpl(createProjectGateway);
    }

    @Bean
    public FindAllProjects findAllProjects(FindAllProjectsGateway findAllProjectsGateway) {
        return new FindAllProjectsImpl(findAllProjectsGateway);
    }

    @Bean
    public RemoveProject removeProject(RemoveProjectGateway removeProjectGateway)
    {
        return new RemoveProjectImpl(removeProjectGateway);
    }
}
