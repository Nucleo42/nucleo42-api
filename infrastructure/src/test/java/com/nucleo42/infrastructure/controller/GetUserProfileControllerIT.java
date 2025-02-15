package com.nucleo42.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nucleo42.entity.UserEntity;
import com.nucleo42.repository.UserEntityRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetUserProfileControllerIT {
    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    UserEntity userEntity;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        userEntity = new UserEntity(null, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false,
                "Biografia", null, LocalDateTime.now(), LocalDateTime.now());

        userRepository.save(userEntity);
    }

    @Test
    void getExistingUserProfile() throws Exception {

        mockMvc.perform(get("/user/{id}", userEntity.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userProfileDoesNotExist() throws Exception {

        mockMvc.perform(get("/user/{id}", "61776d3c-ceaf-4cd0-959c-794ac86f3732"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void invalidUserProfileId() throws Exception {

        mockMvc.perform(get("/user/{id}", "61776"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}