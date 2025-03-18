package com.nucleo42.infrastruture.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nucleo42.application.protocol.TokenDecoder;
import com.nucleo42.application.protocol.TokenGenerator;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
public class JwtAdapter implements TokenGenerator, TokenDecoder {
    private final String secret;
    private final Algorithm algorithm;

    @Override
    public String generate(String payload) {
        var expirationAt = Instant.now().plus(Duration.ofHours(72));

        return JWT.create()
                .withIssuer("nucleo42")
                .withSubject(payload)
                .withExpiresAt(expirationAt)
                .sign(algorithm);
    }

    @Override
    public String decode(String token) {
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception ex) {
            return null;
        }
    }
}
