package com.nucleo42.infrastruture.controller;

import com.nucleo42.entity.Member;
import com.nucleo42.entity.Project;
import com.nucleo42.entity.Technology;
import com.nucleo42.infrastruture.annotation.ApiRequestBody;
import com.nucleo42.infrastruture.entity.ProjectEntity;
import com.nucleo42.usecase.FindAllProjects;
import com.nucleo42.usecase.RemoveProject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name="project")
@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class FindAllProjectController {

    private final FindAllProjects findAllProjects;
    private final RemoveProject removeProject;

    @Operation(
            description = "Find all projects",
            summary = "Find all projects"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Projects finded",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": "12f51b53-1736-40fa-9a7a-4359f22d3865",
                                                    "name": "Nucleo42",
                                                    "description": "Its a nucleo",
                                                    "vacancies": infinite,
                                                    "goal": "DIY",
                                                    "technologies": [],
                                                    "members": [],
                                                    "createdAt": "2025-04-13T12:18:14.404584",
                                                    "updatedAt": "2025-04-13T17:36:13.341982"
                                                }
                                            ]
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
    @GetMapping
    public ResponseEntity<List<Project>> findAll(
            @RequestParam(value = "name", required = false)
            String name,
            @RequestParam(value = "vacancies", required = false)
            Integer vacancies,
            @RequestParam(value = "technologies", required = false)
            List<Long> technologies,
            @RequestParam(value = "members", required = false)
            String memberName,
            @RequestParam(value = "year", required = false)
            Integer year,
            @RequestParam(value = "month", required = false)
            Integer month
    )
    {
        return ResponseEntity.status(HttpStatus.OK).body(findAllProjects.findAll(name, vacancies, technologies, memberName, month, year));
    }


}
