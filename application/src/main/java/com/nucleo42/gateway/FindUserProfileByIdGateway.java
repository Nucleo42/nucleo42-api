package com.nucleo42.gateway;

import java.util.UUID;

import com.nucleo42.entity.User;

public interface FindUserProfileByIdGateway {
    User findUserById(UUID id);
}
