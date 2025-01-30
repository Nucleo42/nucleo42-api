package com.nucleo42;

import java.util.List;
import java.util.UUID;

import com.nucleo42.entity.User;

public interface UserUseCase {
    void create(User user);
    void save(User user);
    User findUserById(UUID userId);
    List<User> getAllUsers();
}
