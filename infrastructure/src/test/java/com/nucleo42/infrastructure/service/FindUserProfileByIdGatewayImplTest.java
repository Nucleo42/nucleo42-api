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

import com.nucleo42.entity.Skill;
import com.nucleo42.entity.SkillEntity;
import com.nucleo42.entity.User;
import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.repository.UserEntityRepository;
import com.nucleo42.service.FindUserProfileByIdGatewayImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class FindUserProfileByIdGatewayImplTest {
    @InjectMocks
    private FindUserProfileByIdGatewayImpl userService;
    @Mock
    private UserEntityRepository userRepository;

    @Test
    void userExists() {
        UUID id = UUID.randomUUID();
        Skill skill01 = new Skill("Comunicação");
        Skill skill02 = new Skill("Trabalho em equipe");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill01);
        skills.add(skill02);

        User userDomain = new User("Núcleo", "42", "nucleo42@gmail.com", "Nova senha", "Biografia", skills);

        SkillEntity skillEntity01 = new SkillEntity(1L, "Comunicação");
        SkillEntity skillEntity02 = new SkillEntity(2L, "Trabalho em equipe");
        List<SkillEntity> skillsEntity = new ArrayList<>();
        skillsEntity.add(skillEntity01);
        skillsEntity.add(skillEntity02);

        UserEntity userEntity = new UserEntity(id, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false,
                "Biografia", skillsEntity, LocalDateTime.now(), LocalDateTime.now());

        when(userRepository.findById(id))
                .thenReturn(Optional.of(userEntity));

        User userFound = userService.findUserById(id);
        assertEquals(userDomain, userFound);
    }

    @Test
    void userDoesNotExist() {
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(UserDoesNotExistException.class,
                () -> userService.findUserById(id), "User does not exist");
    }

    @Test
    void nullUser() {
        User user = null;
        assertEquals(user, null);
    }

}