package com.nucleo42.infrastruture.controller;

import com.nucleo42.entity.Project;
import com.nucleo42.usecase.FindAllProjects;
import com.nucleo42.usecase.RemoveProject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class FindAllProjectController {

    private final FindAllProjects findAllProjects;
    private final RemoveProject removeProject;

    @GetMapping
    public ResponseEntity<List<Project>> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(findAllProjects.findAll());
    }


}
