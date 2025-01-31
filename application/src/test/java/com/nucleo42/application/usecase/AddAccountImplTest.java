package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.Hasher;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddAccountImplTest {
    @InjectMocks
    private AddAccountImpl sut;

    @Mock
    private AddAccountGateway addAccountGateway;

    @Mock
    private Hasher hasher;

    private final User testUser = new User("John", "Doe", "johndoe@mail.com", "Password@123", null, null);

    @BeforeEach
    void setup() {
        when(addAccountGateway.add(testUser)).thenReturn(true);
        when(hasher.hash(testUser.getPassword())).thenReturn("hashedPassword");
    }

    @Test
    @DisplayName("Should call AddAccountGateway with correct value")
    void test01() {
        sut.add(testUser);

        verify(addAccountGateway).add(testUser);
    }

    @Test
    @DisplayName("Should throw UserAlreadyExistsException when AddAccountGateway returns false")
    void test02() {
        when(addAccountGateway.add(testUser)).thenReturn(false);

        assertThrows(UserAlreadyExistsException.class, () -> sut.add(testUser));
    }

    @Test
    @DisplayName("Should call Hasher with correct value")
    void test03() {
        sut.add(testUser);

        verify(hasher).hash("Password@123");
    }

    @Test
    @DisplayName("Should set user password to hashed value")
    void test04() {
        sut.add(testUser);

        assert testUser.getPassword().equals("hashedPassword");
    }
}
