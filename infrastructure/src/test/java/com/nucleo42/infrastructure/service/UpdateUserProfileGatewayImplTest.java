package com.nucleo42.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.nucleo42.entity.SkillEntity;
import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.repository.UserEntityRepository;
import com.nucleo42.service.UpdateUserProfileGatewayImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UpdateUserProfileGatewayImplTest {
    @InjectMocks
    private UpdateUserProfileGatewayImpl userService;
    @Mock
    private UserEntityRepository userRepository;

    @Test
    void userExists() {
        UUID id = UUID.randomUUID();
        SkillEntity skill01 = new SkillEntity(1L, "Comunicação");
        SkillEntity skill02 = new SkillEntity(2L, "Trabalho em equipe");
        List<SkillEntity> skills = new ArrayList<>();
        skills.add(skill01);
        skills.add(skill02);

        UserEntity userEntity = new UserEntity(id, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false,
                "Biografia", skills, LocalDateTime.now(), LocalDateTime.now());

        when(userRepository.findById(id))
                .thenReturn(Optional.of(userEntity));

        UserEntity userFound = userService.findUserById(id);
        assertEquals(userEntity, userFound);

    }

    @Test
    void userDoesNotExist() {
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(UserDoesNotExistException.class,
                () -> userService.findUserById(id), "User does not exist");
    }

    @Test
    void nullUser() {
        UserEntity userEntity = null;
        assertEquals(userEntity, null);
    }
}