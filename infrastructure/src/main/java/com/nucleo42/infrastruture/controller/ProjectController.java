package com.nucleo42.infrastruture.controller;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastruture.annotation.ApiRequestBody;
import com.nucleo42.infrastruture.dto.CreateProjectRequestDTO;
import com.nucleo42.usecase.AddProject;
import com.nucleo42.usecase.FindAllProjects;
import com.nucleo42.usecase.RemoveProject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private AddProject createProjectUseCase;

    @Autowired
    private FindAllProjects findAllProjects;

    @Autowired
    private RemoveProject removeProject;

    @Operation(
            description = "Create a new project",
            summary = "Create Project"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Project created",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Project created successfully")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Internal server error")
                    )
            )
    })
    @ApiRequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "name": "Project Name",
                                        "description": "Its a project",
                                        "vacancies": 1,
                                        "goal": "Make a project"
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateProjectRequestDTO dto)
    {
        var project = new Project(dto.name(), dto.description(), dto.vacancies(), dto.goal(), new ArrayList<>(), new ArrayList<>());
        var result = createProjectUseCase.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(findAllProjects.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> remove(@PathVariable("id") UUID projectId)
    {
        var result = removeProject.remove(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
