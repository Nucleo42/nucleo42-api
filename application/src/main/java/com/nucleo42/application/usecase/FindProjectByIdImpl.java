package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.FindProjectByIdGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.usecase.FindProjectById;

import java.util.UUID;

public class FindProjectByIdImpl implements FindProjectById {

    private FindProjectByIdGateway findProjectByIdGateway;

    @Override
    public Project findById(UUID id) {
        return findProjectByIdGateway.findById(id);
    }
}
