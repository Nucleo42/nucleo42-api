package com.nucleo42.infrastruture.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAdapterTest {
    private JwtAdapter sut;
    private final Algorithm algorithm = Algorithm.HMAC256("secretTest");
    private final String secretTest = "secretTest";
    private final String payloadTest = "test";
    private MockedStatic<JWT> mockedJWT;
    private JWTCreator.Builder mockedBuild;


    @BeforeEach
    void setup() {
        sut = new JwtAdapter(secretTest, algorithm);
        mockedJWT = mockStatic(JWT.class);
        mockedBuild = mock(JWTCreator.Builder.class);

        mockedJWT.when(JWT::create).thenReturn(mockedBuild);
        when(mockedBuild.withIssuer(anyString())).thenReturn(mockedBuild);
        when(mockedBuild.sign(any(Algorithm.class))).thenReturn("tokenTest");
    }

    @AfterEach
    void tearDown() {
        mockedJWT.close();
    }

    @Test
    @DisplayName("Should call JWT.sign with correct value")
    void test01() {
            sut.generate(payloadTest);
            verify(mockedBuild).sign(algorithm);

    }

    @Test
    @DisplayName("Should sign token with issuer nucleo42")
    void test02() {
            sut.generate(payloadTest);
            verify(mockedBuild).withIssuer("nucleo42");
    }
}
