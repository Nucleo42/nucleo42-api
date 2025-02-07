package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginImplTest {
    @InjectMocks
    private LoginImpl sut;

    @Mock
    private LoadUserByEmailGateway loadUserByEmailGateway;

    private final String emailTest = "test@mail.com";
    private final String passwordTest = "Password@123";

    @Test
    @DisplayName("Should call LoadUserByEmailGateway with correct value")
    void test01() {
        sut.login(this.emailTest, this.passwordTest);

        verify(this.loadUserByEmailGateway).load(this.emailTest);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when LoadUserByEmailGateway returns empty")
    void test02() {
        when(this.loadUserByEmailGateway.load(this.emailTest)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> sut.login(this.emailTest, this.passwordTest));
    }
}
