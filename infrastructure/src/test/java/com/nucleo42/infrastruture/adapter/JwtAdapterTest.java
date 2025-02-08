package com.nucleo42.infrastruture.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAdapterTest {
    private JwtAdapter sut;
    private Algorithm algorithm = Algorithm.HMAC256("secretTest");
    private String secretTest = "secretTest";
    private final String payloadTest = "test";


    @BeforeEach
    void setup() {
        sut = new JwtAdapter(secretTest, algorithm);
    }

    @Test
    @DisplayName("Should call JWT.sign with correct value")
    void test01() {
        try (var mockedJWT = mockStatic(JWT.class)) {
            var mockedBuild = mock(JWTCreator.Builder.class);

            mockedJWT.when(JWT::create).thenReturn(mockedBuild);
            when(mockedBuild.sign(any(Algorithm.class))).thenReturn("tokenTest");

            sut.generate(payloadTest);

            verify(mockedBuild).sign(algorithm);
        }
    }

}
