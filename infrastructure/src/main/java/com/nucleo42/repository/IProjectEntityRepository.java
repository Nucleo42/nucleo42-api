package com.nucleo42.repository;

import com.nucleo42.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProjectEntityRepository extends JpaRepository<ProjectEntity, UUID> {
}
