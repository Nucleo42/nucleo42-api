package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.HashGenerator;
import com.nucleo42.entity.User;
import com.nucleo42.exception.AcceptTermsException;
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
    private HashGenerator hashGenerator;

    private final User testUser = new User(null, "John", "Doe", "johndoe@mail.com", "Password@123", null, true, null);

    @BeforeEach
    void setup() {
        lenient().when(addAccountGateway.add(testUser)).thenReturn(true);
        lenient().when(hashGenerator.hash(testUser.getPassword())).thenReturn("hashedPassword");
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

        verify(hashGenerator).hash("Password@123");
    }

    @Test
    @DisplayName("Should set user password to hashed value")
    void test04() {
        sut.add(testUser);

        assert testUser.getPassword().equals("hashedPassword");
    }

    @Test
    @DisplayName("Should return a message on success")
    void test05() {
        assert sut.add(testUser).equals("User registered successfully");
    }

    @Test
    @DisplayName("Should throw AcceptTermsException when user has not accepted terms")
    void test06() {
        var user = new User(null, "John", "Doe", "johndoe@mail.com", "Password@123", null, false, null);

        assertThrows(AcceptTermsException.class, () -> sut.add(user));
    }
}
