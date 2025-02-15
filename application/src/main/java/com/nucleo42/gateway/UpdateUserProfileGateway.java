package com.nucleo42.gateway;

import java.util.UUID;

import com.nucleo42.entity.User;

public interface UpdateUserProfileGateway {
    void update(User userUpdate, UUID id);

}
