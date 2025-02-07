package com.nucleo42.application.gateway;

import com.nucleo42.entity.User;

public interface LoadUserByEmailGateway {
    User load(String email);
}
