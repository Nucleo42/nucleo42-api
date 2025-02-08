package com.nucleo42.infrastruture.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class BCryptAdapterTest {
    private final BCryptAdapter sut = new BCryptAdapter();
    private final String valueTest = "any_value";
    private final String hashedValueTest = "hashed_value";

    @Nested
    @DisplayName("hash method")
    class Hash {
        @Test
        @DisplayName("Should call BCrypt.hashpw with correct values")
        void test01() {
            try (var mockedBcrypt = mockStatic(BCrypt.class)) {
                mockedBcrypt.when(() -> BCrypt.gensalt(anyInt())).thenReturn("salt");
                mockedBcrypt.when(() -> BCrypt.hashpw(anyString(), anyString())).thenReturn("hashed_value");

                sut.hash(valueTest);

                mockedBcrypt.verify(() -> BCrypt.hashpw(valueTest, "salt"));
            }
        }

        @Test
        @DisplayName("Should return hashed value")
        void test02() {
            try (var mockedBcrypt = mockStatic(BCrypt.class)) {
                mockedBcrypt.when(() -> BCrypt.gensalt(anyInt())).thenReturn("salt");
                mockedBcrypt.when(() -> BCrypt.hashpw(anyString(), anyString())).thenReturn("hashed_value");

                var result = sut.hash(valueTest);

                assert result.equals("hashed_value");
            }
    }
    }

    @Nested
    @DisplayName("compare method")
    class Compare {
        @Test
        @DisplayName("Should call BCrypt.checkpw with correct values")
        void test01() {
            try (var mockedBcrypt = mockStatic(BCrypt.class)) {
                mockedBcrypt.when(() -> BCrypt.checkpw(anyString(), anyString())).thenReturn(true);

                sut.compare(valueTest, hashedValueTest);

                mockedBcrypt.verify(() -> BCrypt.checkpw(valueTest, hashedValueTest));
            }
        }

        @Test
        @DisplayName("Should return false when BCrypt.checkpw returns false")
        void test02() {
            try (var mockedBcrypt = mockStatic(BCrypt.class)) {
                mockedBcrypt.when(() -> BCrypt.checkpw(anyString(), anyString())).thenReturn(false);

                var result = sut.compare(valueTest, hashedValueTest);

                assert !result;
            }
        }
    }
}
