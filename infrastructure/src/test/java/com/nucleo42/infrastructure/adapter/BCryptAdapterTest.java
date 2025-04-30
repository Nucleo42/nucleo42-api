package com.nucleo42.infrastructure.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class BCryptAdapterTest {
    private final BCryptAdapter sut = new BCryptAdapter();
    private final String value = "any_value";

    @Test
    @DisplayName("Should call BCrypt.hashpw with correct values")
    void test01() {
        try (var mockedBcrypt = mockStatic(BCrypt.class)) {
            mockedBcrypt.when(() -> BCrypt.gensalt(anyInt())).thenReturn("salt");
            mockedBcrypt.when(() -> BCrypt.hashpw(anyString(), anyString())).thenReturn("hashed_value");

            sut.hash(value);

            mockedBcrypt.verify(() -> BCrypt.hashpw(value, "salt"));
        }
    }

    @Test
    @DisplayName("Should return hashed value")
    void test02() {
        try (var mockedBcrypt = mockStatic(BCrypt.class)) {
            mockedBcrypt.when(() -> BCrypt.gensalt(anyInt())).thenReturn("salt");
            mockedBcrypt.when(() -> BCrypt.hashpw(anyString(), anyString())).thenReturn("hashed_value");

            var result = sut.hash(value);

            assert result.equals("hashed_value");
        }
    }
}
