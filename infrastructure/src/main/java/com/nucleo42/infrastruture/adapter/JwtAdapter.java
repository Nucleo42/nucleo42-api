package com.nucleo42.infrastruture.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nucleo42.application.protocol.TokenGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAdapter implements TokenGenerator {
    private final String secret;
    private final Algorithm algorithm;

    @Override
    public String generate(String payload) {
        return JWT.create()
                .withIssuer("nucleo42")
                .withSubject(payload)
                .sign(algorithm);
    }
}
