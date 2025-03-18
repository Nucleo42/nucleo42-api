package com.nucleo42.infrastruture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleo42.application.protocol.HashGenerator;
import com.nucleo42.entity.User;
import com.nucleo42.infrastruture.dto.LoginRequestDTO;
import com.nucleo42.infrastruture.dto.SignUpRequestDTO;
import com.nucleo42.infrastruture.mapper.UserMapper;
import com.nucleo42.infrastruture.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HashGenerator hashGenerator;

    private final LoginRequestDTO dto = new LoginRequestDTO("johndoe@mail.com", "Password@123");
    private final SignUpRequestDTO dtoSignUp = new SignUpRequestDTO("John", "Doe", "johndoe@mail.com", "Password@123", true);

    @BeforeEach
    void setup() throws Exception {
        userRepository.deleteAll();

        var user = new User(
                null,
                "John",
                "Doe",
                dto.email(),
                hashGenerator.hash(dto.password()),
                "",
                true,
                null
        );
        userRepository.save(UserMapper.toEntity(user));
    }

    @Test
    @DisplayName("Should return 200 on success")
    void test01() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().contains("access_token"));

    }

    @Test
    @DisplayName("Should return 401 on invalid credentials")
    void test02() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequestDTO("test@mail.com", "invalid_password"))))
                .andExpect(status().isUnauthorized())
                .andExpect(result -> result.getResponse().getContentAsString().contains("Invalid credentials"));
    }
}
