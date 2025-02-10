package com.nucleo42.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.UpdateUserProfileCase;
import com.nucleo42.dto.request.UpdateUserRequest;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.mapper.UserMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user/update")
public class UpdateUserProfileController {
    @Autowired
    private UpdateUserProfileCase updateUserCase;

    @PutMapping("{id}")
    public ResponseEntity<?> updateUserProfileById(@PathVariable UUID id,
            @RequestBody @Valid UpdateUserRequest updateUser) {
        try {
            User user = UserMapper.toUser(updateUser);
            updateUserCase.update(user, id);
        } catch (UserDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

}
