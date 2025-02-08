package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.application.protocol.HashCompare;
import com.nucleo42.application.protocol.TokenGenerator;
import com.nucleo42.exception.InvalidCredentialsException;
import com.nucleo42.usecase.Login;

public class LoginImpl implements Login {
    private final LoadUserByEmailGateway loadUserByEmailGateway;
    private final HashCompare hashCompare;
    private final TokenGenerator tokenGenerator;

    public LoginImpl(LoadUserByEmailGateway loadUserByEmailGateway, HashCompare hashCompare, TokenGenerator tokenGenerator) {
        this.loadUserByEmailGateway = loadUserByEmailGateway;
        this.hashCompare = hashCompare;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public String login(String email, String password) {
        var user = this.loadUserByEmailGateway.load(email).orElseThrow(InvalidCredentialsException::new);

        var matchPassword = this.hashCompare.compare(password, user.getPassword());
        if (!matchPassword) {
            throw new InvalidCredentialsException();
        }

        return this.tokenGenerator.generate(user.getId().toString());
    }
}
