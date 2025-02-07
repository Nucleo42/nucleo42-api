package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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
}
