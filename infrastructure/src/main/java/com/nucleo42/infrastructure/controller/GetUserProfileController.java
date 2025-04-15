package com.nucleo42.infrastructure.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.infrastructure.dto.UserResponse;
import com.nucleo42.infrastructure.mapper.UserMapper;
import com.nucleo42.usecase.GetUserProfileByIdCase;

@RestController
@RequestMapping("user")
public class GetUserProfileController {
    @Autowired
    private GetUserProfileByIdCase getUserProfileByIdCase;

    @GetMapping("{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable("id") UUID id) {
        UserResponse user = UserMapper.toResponse(getUserProfileByIdCase.getUserProfileById(id));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
