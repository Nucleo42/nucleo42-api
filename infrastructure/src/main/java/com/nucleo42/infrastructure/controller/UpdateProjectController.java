package com.nucleo42.infrastructure.controller;

import com.nucleo42.entity.Project;
import com.nucleo42.infrastructure.annotation.ApiRequestBody;
import com.nucleo42.infrastructure.dto.UpdateProjectRequestDTO;
import com.nucleo42.usecase.UpdateProject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Tag(name="project")
@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class UpdateProjectController {

    private final UpdateProject updateProjectUseCase;

    @Operation(
            description = "Update a new project",
            summary = "Update Project"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project updated",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Project updated successfully")
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "status": 422,
                                                "message": "Campos inválidos",
                                                "errors": [
                                                    {
                                                        "field": "goal",
                                                        "error": "O objetivo do projeto precisa ser informado"
                                                    },
                                                    {
                                                        "field": "name",
                                                        "error": "O nome do projeto precisa ser informado"
                                                    },
                                                    {
                                                        "field": "description",
                                                        "error": "A descrição do projeto precisa ser informado"
                                                    },
                                                    {
                                                        "field": "vacancies",
                                                        "error": "As vagas do projeto precisam ser informadas"
                                                    }
                                                ]
                                            }
                                            """
                            )

                    )
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
    @PutMapping("{id}")
    public ResponseEntity<String> create(@PathVariable("id") String projectId, @RequestBody @Valid UpdateProjectRequestDTO dto)
    {
        var project = new Project(dto.name(), dto.description(), dto.vacancies(), dto.goal(), new ArrayList<>(), new ArrayList<>());
        project.setId(UUID.fromString(projectId));
        var result = updateProjectUseCase.update(project);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
