package com.nucleo42.infrastructure.repository;

import com.nucleo42.infrastructure.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID>, JpaSpecificationExecutor<ProjectEntity> {
}
