package com.nucleo42.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleo42.dto.request.CreateUserRequest;
import com.nucleo42.dto.request.UpdateUserRequest;
import com.nucleo42.dto.response.BaseResponse;
import com.nucleo42.dto.response.UserResponse;
import com.nucleo42.entity.User;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.usecaseimpl.UserUseCaseImpl;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserUseCaseImpl userUseCase;
    private final UserMapper userMapper;

    public UserController(UserUseCaseImpl createUserUseCase, UserMapper userMapper) {
        this.userUseCase = createUserUseCase;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userMapper.toResponse(userUseCase.getAllUsers());
    }

    @PostMapping("create")
    public BaseResponse<String> createUser(@RequestBody CreateUserRequest createUser) {
        userUseCase.create(userMapper.toUser(createUser));
        return BaseResponse.<String>builder()
                .success(true).message("User created successfully")
                .build();
    }

    @PutMapping("update/{id}")
    public BaseResponse<String> updateUser(@PathVariable("id") UUID userId, @RequestBody UpdateUserRequest updateUser) {
        User user = userUseCase.findUserById(userId);
        BeanUtils.copyProperties(updateUser, user);
        userUseCase.save(user);
        return BaseResponse.<String>builder()
                .success(true)
                .message("User updated successfully")
                .build();
    }
}
