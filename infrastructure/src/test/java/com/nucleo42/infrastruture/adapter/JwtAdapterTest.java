package com.nucleo42.infrastruture.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAdapterTest {
    private JwtAdapter sut;
    private final Algorithm algorithm = Algorithm.HMAC256("secretTest");
    private final String secretTest = "secretTest";
    private final String payloadTest = "test";
    private MockedStatic<JWT> mockedJWT;
    private Verification mockedVerification;
    private JWTVerifier mockedVerifier;
    private DecodedJWT mockedDecodedJWT;
    private JWTCreator.Builder mockedBuild;


    @BeforeEach
    void setup() {
        sut = new JwtAdapter(secretTest, algorithm);
        mockedJWT = mockStatic(JWT.class);
        mockedBuild = mock(JWTCreator.Builder.class);
        mockedVerification = mock(Verification.class);
        mockedVerifier = mock(JWTVerifier.class);
        mockedDecodedJWT = mock(DecodedJWT.class);


        mockedJWT.when(JWT::create).thenReturn(mockedBuild);
        lenient().when(mockedBuild.withIssuer(anyString())).thenReturn(mockedBuild);
        lenient().when(mockedBuild.sign(any(Algorithm.class))).thenReturn("tokenTest");
        lenient().when(mockedBuild.withSubject(anyString())).thenReturn(mockedBuild);
        lenient().when(mockedBuild.withExpiresAt(any(Instant.class))).thenReturn(mockedBuild);
        lenient().when(JWT.require(algorithm)).thenReturn(mockedVerification);
        lenient().when(mockedVerification.build()).thenReturn(mockedVerifier);
        lenient().when(mockedVerifier.verify("tokenTest")).thenReturn(mockedDecodedJWT);
    }

    @AfterEach
    void tearDown() {
        mockedJWT.close();
    }

    @Nested
    @DisplayName("generate method")
    class GenerateTest {
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

        @Test
        @DisplayName("Should sign token with correct subject")
        void test03() {
                sut.generate(payloadTest);
                verify(mockedBuild).withSubject(payloadTest);
        }

        @Test
        @DisplayName("Should sign token with correct expiration")
        void test04() {
            sut.generate(payloadTest);
            var expiresAt = Instant.now().plus(Duration.ofHours(72));
            verify(mockedBuild).withExpiresAt(expiresAt);
        }

        @Test
        @DisplayName("Should return token")
        void test05() {
            var token = sut.generate(payloadTest);
            assert token.equals("tokenTest");
        }
    }

    @Nested
    @DisplayName("decode method")
    class DecodeTest {
        @Test
        @DisplayName("Should call JWT.verify with correct value")
        void test01() {
            sut.decode("tokenTest");
            verify(mockedVerifier).verify("tokenTest");
        }
    }
}
