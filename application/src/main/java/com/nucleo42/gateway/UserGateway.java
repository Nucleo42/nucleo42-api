package com.nucleo42.gateway;

import java.util.List;
import java.util.UUID;

import com.nucleo42.entity.User;

public interface UserGateway {
    void saveUser(User user);
    void create(User user);
    User findUserById(UUID userId);
    List<User> getAllUsers();
}
