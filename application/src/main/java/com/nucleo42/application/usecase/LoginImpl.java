package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.exception.UserNotFoundException;
import com.nucleo42.usecase.Login;

public class LoginImpl implements Login {
    private LoadUserByEmailGateway loadUserByEmailGateway;

    @Override
    public String login(String email, String password) {
        this.loadUserByEmailGateway.load(email).orElseThrow(UserNotFoundException::new);
        return "";
    }
}
