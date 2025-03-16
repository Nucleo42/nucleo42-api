package com.nucleo42.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.UpdateUserProfileCase;
import com.nucleo42.dto.request.UpdateUserRequestDTO;
import com.nucleo42.entity.User;
import com.nucleo42.exception.SkillDoesNotExistException;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.mapper.UserMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/update")
public class UpdateUserProfileController {
    @Autowired
    private UpdateUserProfileCase updateUserCase;

    @PutMapping
    public ResponseEntity<?> updateUserProfileById(@RequestBody @Valid UpdateUserRequestDTO updateUser) {
        try {
            User user = UserMapper.toUser(updateUser);
            updateUserCase.update(user);
        } catch (UserDoesNotExistException | SkillDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

}
