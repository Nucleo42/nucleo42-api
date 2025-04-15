package com.nucleo42.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.infrastructure.dto.UpdateUserRequestDTO;
import com.nucleo42.entity.User;
import com.nucleo42.infrastructure.mapper.UserMapper;
import com.nucleo42.usecase.UpdateUserProfileCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/update")
public class UpdateUserProfileController {
    @Autowired
    private UpdateUserProfileCase updateUserCase;

    @PutMapping
    public ResponseEntity<?> updateUserProfileById(@RequestBody @Valid UpdateUserRequestDTO updateUser) {
        User user = UserMapper.toDomain(updateUser);
        updateUserCase.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

}
