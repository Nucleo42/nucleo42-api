package com.nucleo42.infrastruture.mapper;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectEntity toProjectEntity(Project project)
    {
        return new ProjectEntity(
                null,
                project.getName(),
                project.getDescription(),
                project.getVacancies(),
                project.getGoal(),
                null,
                null,
                null
        );
    }
}
