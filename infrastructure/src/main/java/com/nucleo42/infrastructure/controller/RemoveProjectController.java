package com.nucleo42.infrastructure.controller;

import com.nucleo42.usecase.RemoveProject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@Tag(name = "project")
@RestController
@RequestMapping("/project")
public class RemoveProjectController {

    private final RemoveProject removeProject;

    @Operation(
            description = "Delete a project",
            summary = "Delete Project"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project deleted",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Project deleted successfully")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
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
    @DeleteMapping("{id}")
    public ResponseEntity<String> remove(@PathVariable("id") UUID projectId)
    {
        var result = removeProject.remove(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
