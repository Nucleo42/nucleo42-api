package com.nucleo42.infrastruture.mapper;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.entity.ProjectEntity;

import java.util.List;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project)
    {
        return new ProjectEntity(
                null,
                project.getName(),
                project.getDescription(),
                project.getVacancies(),
                project.getGoal(),
                List.of(),
                List.of(),
                null,
                null
        );
    }

    public static Project fromEntity(ProjectEntity projectEntity)
    {
        return new Project(
                projectEntity.getId(),
                projectEntity.getName(),
                projectEntity.getDescription(),
                projectEntity.getVacancies(),
                projectEntity.getGoal(),
                List.of(),
                List.of(),
                projectEntity.getCreatedAt(),
                projectEntity.getUpdatedAt()
        );
    }
}
