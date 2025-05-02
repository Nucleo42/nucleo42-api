package com.nucleo42.infrastructure.service;

import com.nucleo42.application.gateway.AddProjectGateway;
import com.nucleo42.application.gateway.FindAllProjectsGateway;
import com.nucleo42.application.gateway.RemoveProjectGateway;
import com.nucleo42.application.gateway.UpdateProjectGateway;
import com.nucleo42.entity.Project;
import com.nucleo42.infrastructure.entity.ProjectEntity;
import com.nucleo42.infrastructure.mapper.ProjectMapper;
import com.nucleo42.infrastructure.repository.ProjectRepository;
import com.nucleo42.usecase.FindProjectById;
import com.nucleo42.usecase.findall.FindAllProjectsParams;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectGateway implements AddProjectGateway, FindAllProjectsGateway, FindProjectById, RemoveProjectGateway, UpdateProjectGateway {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Boolean create(Project project) {
        projectRepository.save(ProjectMapper.toEntity(project));
        return true;
    }

    @Override
    public List<Project> findAll(FindAllProjectsParams dto) {

        Specification<ProjectEntity> specifications = Specification.where((root, query, cb) -> cb.conjunction());

        if (dto.name() != null)
        {
            Specification<ProjectEntity> nameSpecification = (root, query, cb) -> {
                return cb.like(cb.lower(root.get("name")), "%" + dto.name().toLowerCase() + "%");
            };
            specifications = specifications.and(nameSpecification);
        }

        if (dto.vacancies() != null)
        {
            Specification<ProjectEntity> vacanciesSpecification = (root, query, cb) ->
            {
                return cb.equal(root.get("vacancies"), dto.vacancies());
            };
            specifications = specifications.and(vacanciesSpecification);
        }

        if (dto.technologies() != null && !dto.technologies().isEmpty())
        {
            Specification<ProjectEntity> technologySpecification = (root, query, cb) ->
            {
                return root.get("technologies").in(dto.technologies());
            };
            specifications = specifications.and(technologySpecification);
        }

        if (dto.memberName() != null)
        {
            Specification<ProjectEntity> memberNameSpecification = (root, query, cb) ->
            {
                Join<Object, Object> project = root.join("project_members", JoinType.INNER);

                return cb.like(cb.lower(project.get("user.firstName")) , "%" + dto.memberName().toLowerCase() + "%");
            };
            specifications = specifications.and(memberNameSpecification);
        }

        if (dto.year() != null)
        {
            Specification<ProjectEntity> yearSpecification = (root, query, cb) -> {
                return cb.equal(
                        cb.function("to_char", String.class, root.get("createdAt"),
                                cb.literal("YYYY"))
                        , dto.year().toString());
            };

            specifications = specifications.and(yearSpecification);
        }

        if (dto.month() != null)
        {
            String formattedMonth = String.format("%02d", dto.month());
            Specification<ProjectEntity> monthSpecification = (root, query, cb) ->
            {
                return cb.equal(
                        cb.function("to_char", String.class, root.get("createdAt"),
                                cb.literal("MM"))
                        , formattedMonth);
            };
            specifications = specifications.and(monthSpecification);
        }

        return projectRepository.findAll(specifications).stream().map(ProjectMapper::fromEntity).toList();
    }

    @Override
    public Boolean remove(UUID id) {
        projectRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean update(Project project) {
        if (project.getId() == null)
        {
            return false;
        }

        Optional<ProjectEntity> projectOptional = projectRepository.findById(project.getId());

        if (projectOptional.isPresent())
        {
            ProjectEntity projectData = projectOptional.get();

            projectData.setName(project.getName());
            projectData.setGoal(project.getGoal());
            projectData.setDescription(project.getDescription());
            projectData.setVacancies(project.getVacancies());

            projectRepository.save(projectData);
            return true;
        }
        return false;
    }

    @Override
    public Project findById(UUID id) {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(id);
        return projectEntity.map(ProjectMapper::fromEntity).orElse(null);
    }
}

