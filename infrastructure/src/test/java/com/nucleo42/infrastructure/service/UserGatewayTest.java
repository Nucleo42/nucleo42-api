package com.nucleo42.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.nucleo42.entity.Skill;
import com.nucleo42.entity.User;
import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.exception.UserIdIsInvalidException;
import com.nucleo42.exception.UserIdIsNullException;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.repository.UserEntityRepository;
import com.nucleo42.service.UserGateway;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserGatewayTest {
    @Autowired
    private UserEntityRepository repository;
    @Autowired
    private UserGateway userService;

    private User userDomain;
    private UserEntity userEntity;

    private UUID id = UUID.fromString("7be9b195-6c72-4ea3-a51b-01b8fada2e4c");
    private final List<Skill> skills = List.of(new Skill("Trabalho em equipe"), new Skill("Comunicação"));

    @BeforeEach
    void setup() {
        repository.deleteAll();

        userEntity = new UserEntity(null, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false,
                "Biografia", null, null, null, null);

        userEntity = repository.save(userEntity);
        userDomain = UserMapper.toUser(userEntity);
    }

    @Nested
    class getUserProfileById {
        @Test
        @DisplayName("Should return user if it exists")
        void getUserProfileByIdCase01() {
            User user = userService.getUserProfileById(userEntity.getId());
            user.setSkills(null);
            assertEquals(user, userDomain);
        }

        @Test
        @DisplayName("Should return UserDoesNotExistException if the user id has no match in the database")
        void getUserProfileByIdCase02() {
            Exception thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
                userService.getUserProfileById(id);
            });

            Assertions.assertEquals("User id '7be9b195-6c72-4ea3-a51b-01b8fada2e4c' does not exist",
                    thrown.getMessage());
        }

        @Test
        @DisplayName("Should return UserIdIsNullException if user id is null")
        void getUserProfileByIdCase03() {
            Assertions.assertThrows(UserIdIsNullException.class, () -> {
                userService.getUserProfileById(null);
            });
        }

    }

    @Nested
    class updateMethod {
        @Test
        @DisplayName("Should return true if the user was updated successfully")
        void updateMethodCase01() {
            assertTrue(userService.update(userDomain));
        }

        @Test
        @DisplayName("Should return UserDoesNotExistException if the user id has no match in the database")
        void updateMethodCase02() {
            Exception thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
                userDomain.setId(id);
                userService.update(userDomain);
            });

            Assertions.assertEquals("User id '7be9b195-6c72-4ea3-a51b-01b8fada2e4c' does not exist",
                    thrown.getMessage());
        }

        @Test
        @DisplayName("Should return false if user is null")
        void updateMethodCase03() {
            assertFalse(userService.update(null));
        }

        @Test
        @DisplayName("Should return UserIdIsNullException if user id is null")
        void updateMethod04() {
            userDomain.setId(null);

            Assertions.assertThrows(UserIdIsNullException.class, () -> {
                userService.update(userDomain);
            });

        }

    }

    @Nested
    class updateSkillsMethod {
        @Test
        @DisplayName("Should return true if the skills was updated successfully")
        void updateSkillsMethodCase01() {
            assertTrue(userService.updateSkills(skills, userEntity.getId().toString()));
        }

        @Test
        @DisplayName("Should return false if skills is null")
        void updateSkillsMethodCase02() {
            assertFalse(userService.updateSkills(null, id.toString()));
        }

        @Test
        @DisplayName("Should return UserIdIsInvalidException if user id is null")
        void updateSkillsMethodCase03() {
            Assertions.assertThrows(UserIdIsInvalidException.class, () -> {
                userService.updateSkills(skills, null);
            });
        }

        @Test
        @DisplayName("Should return UserDoesNotExistException if the user id has no match in the database")
        void updateSkillsMethodCase04() {
            Exception thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
                userService.updateSkills(skills, id.toString());
            });

            Assertions.assertEquals("User id '7be9b195-6c72-4ea3-a51b-01b8fada2e4c' does not exist",
                    thrown.getMessage());
        }
    }

}
