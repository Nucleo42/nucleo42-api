package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.ICreateProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.exception.InternalServerErrorException;
import com.nucleo42.usecase.ICreateProjectUseCase;

public class CreateProjectUseCaseImpl implements ICreateProjectUseCase {

    private final ICreateProjectGateway createProjectGateway;

    public CreateProjectUseCaseImpl(ICreateProjectGateway createProjectGateway)
    {
        this.createProjectGateway = createProjectGateway;
    }

    @Override
    public String create(Project project) {
        var result = createProjectGateway.create(project);

        if (!result)
        {
           throw new InternalServerErrorException();
        }

        return "Project created successfully";
    }
}
