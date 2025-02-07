package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.usecase.Login;

public class LoginImpl implements Login {
    private LoadUserByEmailGateway loadUserByEmailGateway;

    @Override
    public String login(String email, String password) {
        this.loadUserByEmailGateway.load(email);
        return "";
    }
}
