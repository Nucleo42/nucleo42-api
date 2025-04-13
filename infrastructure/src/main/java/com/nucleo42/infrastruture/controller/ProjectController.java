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
    private FindAllProjects findAllProjects;

    @Autowired
    private RemoveProject removeProject;



    @GetMapping
    public ResponseEntity<List<Project>> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(findAllProjects.findAll());
    }


}
