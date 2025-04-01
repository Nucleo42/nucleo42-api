package com.nucleo42.infrastruture.controller;

import com.nucleo42.usecase.RemoveProject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/project")
public class RemoveProjectController {

    private final RemoveProject removeProject;

    public RemoveProjectController(RemoveProject removeProject)
    {
        this.removeProject = removeProject;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> remove(@PathVariable("id") UUID projectId)
    {
        var result = removeProject.remove(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
