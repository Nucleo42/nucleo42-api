package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddAccountImplTest {
    @InjectMocks
    private AddAccountImpl sut;

    @Mock
    private AddAccountGateway addAccountGateway;

    @Test
    @DisplayName("Should call AddAccountGateway with correct value")
    void test01() {
        var user = new User("John", "Doe", "johndoe@mail.com", "Password@123", null, null);
        sut.add(user);

        verify(addAccountGateway).add(user);
    }

    @Test
    @DisplayName("Should throw UserAlreadyExistsException when AddAccountGateway returns false")
    void test02() {
        var user = new User("John", "Doe", "johndoe@mail.com", "Password@123", null, null);
        when(addAccountGateway.add(user)).thenReturn(false);

        assertThrows(UserAlreadyExistsException.class, () -> sut.add(user));
    }
}
