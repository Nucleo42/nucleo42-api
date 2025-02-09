package com.nucleo42.infrastruture.config;

import com.nucleo42.application.gateway.ICreateProjectGateway;
import com.nucleo42.application.usecase.CreateProjectUseCaseImpl;
import com.nucleo42.infrastruture.service.CreateProjectGatewayImpl;
import com.nucleo42.usecase.ICreateProjectUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ICreateProjectUseCase createProjectUseCase(ICreateProjectGateway createProjectGateway) {
        return new CreateProjectUseCaseImpl(createProjectGateway);
    }

    @Bean
    public ICreateProjectGateway createProjectGateway() {
        return new CreateProjectGatewayImpl();
    }
}
