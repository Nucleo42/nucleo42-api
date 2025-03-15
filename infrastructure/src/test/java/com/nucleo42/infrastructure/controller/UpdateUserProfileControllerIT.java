package com.nucleo42.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleo42.dto.request.UpdateUserRequestDTO;
import com.nucleo42.entity.UserEntity;
import com.nucleo42.repository.UserEntityRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UpdateUserProfileControllerIT {
    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserEntity userEntity;
    @Autowired
    private ObjectMapper objectMapper;
    private UpdateUserRequestDTO userRequest;
    private UpdateUserRequestDTO userNotExistRequest;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        userEntity = new UserEntity(null, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false, "Biografia",
                null, null, null, null);
        userEntity = userRepository.save(userEntity);

        userRequest = new UpdateUserRequestDTO(userEntity.getId().toString(), "Núcleo2", "422", "nucleo422@gmail.com", "Nova senha2", " ");

        userNotExistRequest = new UpdateUserRequestDTO("61776d3c-ceaf-4cd0-959c-794ac86f3732", "Núcleo2", "422", "nucleo422@gmail.com", "Nova senha2", " ");
    }

    @Test
    @DisplayName("Should return code 200 and the user has been updated successfully")
    void updateUserProfileCase01() throws JsonProcessingException, Exception {
        mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return code 404 if the user was updated successfully")
    void updateUserProfileCase02() throws JsonProcessingException, Exception {
        mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userNotExistRequest)))
                .andExpect(status().isNotFound());
    }

}
