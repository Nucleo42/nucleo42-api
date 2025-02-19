package com.nucleo42.infrastruture.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AuthMiddlewareTest {
    @InjectMocks
    private AuthMiddleware sut;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private MockFilterChain filterChain = new MockFilterChain();

    @Test
    @DisplayName("Should allow public routes")
    void test01() throws Exception {
        request.setRequestURI("/signup");
        sut.doFilterInternal(request, response, filterChain);
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Should return 401 if header is missing")
    void test02() throws Exception {
        request.setRequestURI("/private-route");
        sut.doFilterInternal(request, response, filterChain);
        assertEquals(401, response.getStatus());
    }
}
