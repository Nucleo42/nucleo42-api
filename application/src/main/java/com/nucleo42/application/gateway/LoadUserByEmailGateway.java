package com.nucleo42.application.gateway;

import com.nucleo42.entity.User;

import java.util.Optional;

public interface LoadUserByEmailGateway {
    Optional<User> load(String email);
}
