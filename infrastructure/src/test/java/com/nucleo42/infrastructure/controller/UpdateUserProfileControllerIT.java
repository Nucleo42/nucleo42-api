package com.nucleo42.infrastructure.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.repository.UserEntityRepository;
import com.nucleo42.service.UpdateUserProfileGatewayImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UpdateUserProfileControllerIT {
    @Mock
    private UserEntityRepository userRepository;
    @InjectMocks
    private UpdateUserProfileGatewayImpl updateService;
    private UserEntity userEntity;
    private UUID id = UUID.randomUUID();

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        userEntity = new UserEntity(null, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true,
                false,
                "Biografia", null, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void userProfileExists() {
        when(userRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));
    }

    @Test
    void userProfileDoesNotExist() {
        Assertions.assertThrows(UserDoesNotExistException.class,
                () -> updateService.findUserById(id), "User does not exist");
    }

    @Test
    void userProfileIsNull(){
        UserEntity user = null;
        Assertions.assertThrows(NullPointerException.class,
                () -> userRepository.findById(user.getId()));
    }

}
