package com.nucleo42.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.FindUserProfileByIdCase;
import com.nucleo42.SaveUserProfileCase;
import com.nucleo42.UpdateUserProfileCase;
import com.nucleo42.dto.request.UpdateUserRequest;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.mapper.UserMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("user/update")
public class UpdateUserProfileController {
    private final FindUserProfileByIdCase findUserCase;
    private final UserMapper userMapper;
    private final UpdateUserProfileCase updateUserCase;

    public UpdateUserProfileController(FindUserProfileByIdCase findUserCase, UserMapper userMapper,
            UpdateUserProfileCase updateUserCase) {
        this.findUserCase = findUserCase;
        this.userMapper = userMapper;
        this.updateUserCase = updateUserCase;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUserProfileById(@PathVariable UUID id, @RequestBody UpdateUserRequest updateUser) {
        try {
            User user = findUserCase.findById(id);
            user = userMapper.toUser(updateUser);
            updateUserCase.update(user);
        } catch (UserDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

}
