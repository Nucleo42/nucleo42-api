package com.nucleo42.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.FindUserProfileByIdCase;
import com.nucleo42.dto.response.UserResponse;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.mapper.UserMapper;

@RestController
@RequestMapping("user")
public class GetUserProfileController {
    @Autowired
    private FindUserProfileByIdCase findUserProfileByIdCase;

    @GetMapping("{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable UUID id) {
        UserResponse user;
        try {
            user = UserMapper.toResponse(findUserProfileByIdCase.findById(id));
        } catch (UserDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
