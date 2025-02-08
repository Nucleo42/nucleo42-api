package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.application.protocol.HashCompare;
import com.nucleo42.application.protocol.TokenGenerator;
import com.nucleo42.entity.User;
import com.nucleo42.exception.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginImplTest {
    @InjectMocks
    private LoginImpl sut;

    @Mock
    private LoadUserByEmailGateway loadUserByEmailGateway;

    @Mock
    private HashCompare hashCompare;

    @Mock
    private TokenGenerator tokenGenerator;

    private final User testUser = new User();
    private final String emailTest = "test@mail.com";
    private final String passwordTest = "Password@123";

    @BeforeEach
    void setup() {
        this.testUser.setId(UUID.randomUUID());
        this.testUser.setPassword("hashedPassword");


        lenient().when(this.loadUserByEmailGateway.load(this.emailTest)).thenReturn(Optional.of(this.testUser));
        lenient().when(this.hashCompare.compare(this.passwordTest, this.testUser.getPassword())).thenReturn(true);
        lenient().when(this.tokenGenerator.generate(this.testUser.getId().toString())).thenReturn("token");
    }

    @Test
    @DisplayName("Should call LoadUserByEmailGateway with correct value")
    void test01() {
        sut.login(this.emailTest, this.passwordTest);

        verify(this.loadUserByEmailGateway).load(this.emailTest);
    }

    @Test
    @DisplayName("Should throw InvalidCredentialsException when LoadUserByEmailGateway returns empty")
    void test02() {
        when(this.loadUserByEmailGateway.load(this.emailTest)).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> sut.login(this.emailTest, this.passwordTest));
    }

    @Test
    @DisplayName("Should call HashCompare with correct values")
    void test03() {
        sut.login(this.emailTest, this.passwordTest);

        verify(this.hashCompare).compare(this.passwordTest, this.testUser.getPassword());
    }

    @Test
    @DisplayName("Should throw InvalidCredentialsException when HashCompare returns false")
    void test04() {
        when(this.hashCompare.compare(this.passwordTest, this.testUser.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> sut.login(this.emailTest, this.passwordTest));
    }

    @Test
    @DisplayName("Should call TokenGenerator with correct value")
    void test05() {
        sut.login(this.emailTest, this.passwordTest);

        verify(this.tokenGenerator).generate(this.testUser.getId().toString());
    }

    @Test
    @DisplayName("Should return a token on success")
    void test06() {
        var result = sut.login(this.emailTest, this.passwordTest);

        assert result.equals("token");
    }
}
