package com.nucleo42.infrastructure.config;

import com.nucleo42.application.gateway.*;
import com.nucleo42.application.usecase.*;
import com.nucleo42.infrastructure.service.ProjectGateway;
import com.nucleo42.usecase.AddProject;
import com.nucleo42.usecase.FindProjectById;
import com.nucleo42.usecase.findall.FindAllProjects;
import com.nucleo42.usecase.RemoveProject;
import com.nucleo42.usecase.UpdateProject;
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
    public FindProjectById findProjectById(FindProjectByIdGateway findProjectByIdGateway) {
        return new FindProjectByIdImpl(findProjectByIdGateway);
    }

    @Bean
    public RemoveProject removeProject(RemoveProjectGateway removeProjectGateway)
    {
        return new RemoveProjectImpl(removeProjectGateway);
    }

    @Bean
    public UpdateProject updateProject(UpdateProjectGateway updateProjectGateway)
    {
        return new UpdateProjectImpl(updateProjectGateway);
    }
}
