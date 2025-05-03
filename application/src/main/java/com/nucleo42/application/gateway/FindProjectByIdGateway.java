package com.nucleo42.application.gateway;

import com.nucleo42.entity.Project;

import java.util.UUID;

public interface FindProjectByIdGateway {
    public Project findById(UUID id);
}
