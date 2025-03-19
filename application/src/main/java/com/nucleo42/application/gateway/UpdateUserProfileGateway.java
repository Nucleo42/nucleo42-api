package com.nucleo42.application.gateway;


import com.nucleo42.entity.User;

public interface UpdateUserProfileGateway {
    boolean update(User userUpdate);

}
