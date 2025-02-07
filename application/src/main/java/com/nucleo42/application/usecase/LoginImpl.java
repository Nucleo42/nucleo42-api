package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.application.protocol.HashCompare;
import com.nucleo42.exception.InvalidCredentialsException;
import com.nucleo42.usecase.Login;

public class LoginImpl implements Login {
    private LoadUserByEmailGateway loadUserByEmailGateway;
    private HashCompare hashCompare;

    @Override
    public String login(String email, String password) {
        var user =this.loadUserByEmailGateway.load(email).orElseThrow(InvalidCredentialsException::new);

        this.hashCompare.compare(password, user.getPassword());

        return "";
    }
}
