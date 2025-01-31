package com.nucleo42.infrastruture.adapter;

import com.nucleo42.adapter.BCryptAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class BCryptAdapterTest {
    private final BCryptAdapter sut = new BCryptAdapter();

    @Test
    @DisplayName("Should call BCrypt.hashpw with correct values")
    void test01() {
        String value = "any_value";

        try (var mockedBcrypt = mockStatic(BCrypt.class)) {
            mockedBcrypt.when(() -> BCrypt.gensalt(anyInt())).thenReturn("salt");
            mockedBcrypt.when(() -> BCrypt.hashpw(anyString(), anyString())).thenReturn("hashed_value");

            sut.hash(value);

            mockedBcrypt.verify(() -> BCrypt.hashpw(value, "salt"));
        }
    }
}
