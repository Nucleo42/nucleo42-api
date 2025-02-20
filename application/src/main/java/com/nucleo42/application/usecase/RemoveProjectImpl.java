package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.RemoveProjectGateway;
import com.nucleo42.usecase.RemoveProject;

import java.util.UUID;

public class RemoveProjectImpl implements RemoveProject {

    private RemoveProjectGateway removeProjectGateway;

    public RemoveProjectImpl(RemoveProjectGateway removeProjectGateway) {
        this.removeProjectGateway = removeProjectGateway;
    }

    @Override
    public String remove(UUID id) {
        this.removeProjectGateway.remove(id);
        return "Project successfully removed";
    }
}
