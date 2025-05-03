package com.nucleo42.usecase;

import com.nucleo42.entity.Project;

import java.util.UUID;

public interface FindProjectById {
    public Project findById(UUID id);
}
