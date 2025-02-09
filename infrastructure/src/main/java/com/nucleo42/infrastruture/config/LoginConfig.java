package com.nucleo42.infrastruture.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.application.protocol.HashCompare;
import com.nucleo42.application.protocol.TokenGenerator;
import com.nucleo42.application.usecase.LoginImpl;
import com.nucleo42.infrastruture.adapter.BCryptAdapter;
import com.nucleo42.infrastruture.adapter.JwtAdapter;
import com.nucleo42.infrastruture.service.UserGateway;
import com.nucleo42.usecase.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {
    @Bean
    public Login login(LoadUserByEmailGateway loadUserByEmailGateway, HashCompare hashCompare, TokenGenerator tokenGenerator) {
        return new LoginImpl(loadUserByEmailGateway, hashCompare, tokenGenerator);
    }

    @Bean
    public LoadUserByEmailGateway loadUserByEmailGateway() {
        return new UserGateway();
    }

    @Bean
    public HashCompare hashCompare() {
        return new BCryptAdapter();
    }

    @Bean
    public TokenGenerator tokenGenerator() {
        var secret = System.getenv("JWT_SECRET");
        var algorithm = Algorithm.HMAC256(secret);
        return new JwtAdapter(secret, algorithm);
    }
}
