package com.nucleo42.application.gateway;

import java.util.UUID;

import com.nucleo42.entity.User;

public interface IGetUserProfileByIdGateway {
    User getUserProfileById(UUID id);
}
