package com.nucleo42.infrastruture.controller;

import com.nucleo42.application.usecase.CreateProjectUseCaseImpl;
import com.nucleo42.entity.Project;
import com.nucleo42.exception.InternalServerErrorException;
import com.nucleo42.infrastruture.dto.CreateProjectRequestDTO;
import com.nucleo42.infrastruture.service.CreateProjectGatewayImpl;
import com.nucleo42.usecase.ICreateProjectUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ICreateProjectUseCase createProjectUseCase;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateProjectRequestDTO dto)
    {
        try {
            var project = new Project(dto.name(), dto.description(), dto.vacancies(), dto.goal(), new ArrayList<>(), new ArrayList<>());
            var result = createProjectUseCase.create(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (InternalServerErrorException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
