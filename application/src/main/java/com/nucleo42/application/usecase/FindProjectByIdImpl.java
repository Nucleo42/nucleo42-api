package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.FindProjectByIdGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.usecase.FindProjectById;

import java.util.UUID;

public class FindProjectByIdImpl implements FindProjectById {

    private final FindProjectByIdGateway findProjectByIdGateway;

    public FindProjectByIdImpl(FindProjectByIdGateway findProjectByIdGateway)
    {
        this.findProjectByIdGateway = findProjectByIdGateway;
    }

    @Override
    public Project findById(UUID id) {
        return findProjectByIdGateway.findById(id);
    }
}
