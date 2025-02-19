package com.nucleo42.infrastruture.security;

import com.nucleo42.application.protocol.TokenDecoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AuthMiddlewareTest {
    @InjectMocks
    private AuthMiddleware sut;

    @Mock
    private TokenDecoder tokenDecoder;

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

    @Test
    @DisplayName("Should return 401 if header not starts with Bearer")
    void test03() throws Exception {
        request.setRequestURI("/private-route");
        request.addHeader("Authorization", "token");

        sut.doFilterInternal(request, response, filterChain);

        assertEquals(401, response.getStatus());
    }

    @Test
    @DisplayName("Should call TokenDecoder with correct value")
    void test04() throws Exception {
        request.setRequestURI("/private-route");
        request.addHeader("Authorization", "Bearer token");

        sut.doFilterInternal(request, response, filterChain);

        verify(tokenDecoder).decode("token");
    }

    @Test
    @DisplayName("Should return 401 if token is invalid")
    void test05() throws Exception {
        request.setRequestURI("/private-route");
        request.addHeader("Authorization", "Bearer token");
        when(tokenDecoder.decode("token")).thenReturn(null);

        sut.doFilterInternal(request, response, filterChain);

        assertEquals(401, response.getStatus());
    }

    @Test
    @DisplayName("Should set user_id in request attribute")
    void test06() throws Exception {
        request.setRequestURI("/private-route");
        request.addHeader("Authorization", "Bearer token");
        when(tokenDecoder.decode("token")).thenReturn("subject");

        sut.doFilterInternal(request, response, filterChain);

        assertEquals("subject", request.getAttribute("user_id"));
    }
}
