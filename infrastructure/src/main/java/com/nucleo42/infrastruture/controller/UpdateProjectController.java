package com.nucleo42.infrastruture.controller;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.dto.UpdateProjectRequestDTO;
import com.nucleo42.usecase.UpdateProject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class UpdateProjectController {

    private final UpdateProject updateProjectUseCase;

    @PutMapping("{id}")
    public ResponseEntity<String> create(@PathVariable("id") String projectId, @RequestBody @Valid UpdateProjectRequestDTO dto)
    {
        var project = new Project(dto.name(), dto.description(), dto.vacancies(), dto.goal(), new ArrayList<>(), new ArrayList<>());
        project.setId(UUID.fromString(projectId));
        var result = updateProjectUseCase.update(project);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
