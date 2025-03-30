package com.nucleo42.infrastructure.service;

import com.nucleo42.entity.Skill;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.exception.UserIdIsInvalidException;
import com.nucleo42.exception.UserIdIsNullException;
import com.nucleo42.infrastructure.entity.UserEntity;
import com.nucleo42.infrastructure.mapper.UserMapper;
import com.nucleo42.infrastructure.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserGatewayTest {
    @InjectMocks
    private UserGateway sut;

    @Mock
    private UserRepository repositoryMock;

    @Mock
    private UserEntity userEntityMock;

    @Mock
    private User user;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserGateway userService;

    private User userDomain;

    private UserEntity userEntity;

    private UUID id = UUID.fromString("7be9b195-6c72-4ea3-a51b-01b8fada2e4c");

    private final List<Skill> skills = List.of(new Skill("Trabalho em equipe"), new Skill("Comunicação"));

    @BeforeEach
    void setup() {
        user.setEmail("test@mail.com");

        repository.deleteAll();

        userEntity = new UserEntity(null, "Núcleo", "42", "nucleo42@gmail.com", "Nova senha", true, false,
                "Biografia", null, null, null, null);

        userEntity = repository.save(userEntityMock);
        userDomain = UserMapper.toDomain(userEntityMock);
    }

    @Nested
    @DisplayName("add method")
    class Add {
        @Test
        @DisplayName("Should call UserRepository.save with correct value")
        void test01() {
            try (var mockedUserMapper = mockStatic(UserMapper.class)) {
                mockedUserMapper.when(() -> UserMapper.toEntity(user)).thenReturn(userEntityMock);

                sut.add(user);

                verify(repositoryMock).save(userEntityMock);
            }
        }

        @Test
        @DisplayName("Should call UserRepository.existsByEmail with correct value")
        void test02() {
            try (var mockedUserMapper = mockStatic(UserMapper.class)) {
                mockedUserMapper.when(() -> UserMapper.toEntity(user)).thenReturn(userEntityMock);

                sut.add(user);

                verify(repository).existsByEmail(userEntityMock.getEmail());
            }
        }

        @Test
        @DisplayName("Should return false if UserRepository.existsByEmail returns true")
        void test03() {
            try (var mockedUserMapper = mockStatic(UserMapper.class)) {
                mockedUserMapper.when(() -> UserMapper.toEntity(user)).thenReturn(userEntityMock);
                when(repository.existsByEmail(userEntityMock.getEmail())).thenReturn(true);

                var result = sut.add(user);

                assert result.equals(false);
            }
        }

        @Test
        @DisplayName("Should return true on success")
        void test04() {
            try (var mockedUserMapper = mockStatic(UserMapper.class)) {
                mockedUserMapper.when(() -> UserMapper.toEntity(user)).thenReturn(userEntityMock);
                when(repository.existsByEmail(userEntityMock.getEmail())).thenReturn(false);

                var result = sut.add(user);

                assert result.equals(true);
            }
        }
    }

    @Nested
    @DisplayName("load method")
    class Load {
        @Test
        @DisplayName("Should call UserRepository.findByEmail with correct value")
        void test01() {
            sut.load(user.getEmail());

            verify(repository).findByEmail(user.getEmail());
        }

        @Test
        @DisplayName("Should return Optional.empty if UserRepository.findByEmail returns null")
        void test02() {
            var result = sut.load(user.getEmail());

            assert result.isEmpty();
        }

        @Test
        @DisplayName("Should return Optional.of(User) if UserRepository.findByEmail returns UserEntity")
        void test03() {
            when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntityMock));

            var result = sut.load(user.getEmail());

            assert result.isPresent();
        }
    }

    @Nested
    class getUserProfileById {
        @Test
        @DisplayName("Should return user if it exists")
        void test01() {
            User user = userService.getUserProfileById(userEntity.getId());
            user.setSkills(null);
            assertEquals(user, userDomain);
        }

        @Test
        @DisplayName("Should return UserDoesNotExistException if the user id has no match in the database")
        void test02() {
            Exception thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
                userService.getUserProfileById(id);
            });

            Assertions.assertEquals("User id '7be9b195-6c72-4ea3-a51b-01b8fada2e4c' does not exist",
                    thrown.getMessage());
        }

        @Test
        @DisplayName("Should return UserIdIsNullException if user id is null")
        void test03() {
            Assertions.assertThrows(UserIdIsNullException.class, () -> {
                userService.getUserProfileById(null);
            });
        }

    }

    @Nested
    class updateMethod {
        @Test
        @DisplayName("Should return true if the user was updated successfully")
        void test01() {
            assertTrue(userService.update(userDomain));
        }

        @Test
        @DisplayName("Should return UserDoesNotExistException if the user id has no match in the database")
        void test02() {
            Exception thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
                userDomain.setId(id);
                userService.update(userDomain);
            });

            Assertions.assertEquals("User id '7be9b195-6c72-4ea3-a51b-01b8fada2e4c' does not exist",
                    thrown.getMessage());
        }

        @Test
        @DisplayName("Should return false if user is null")
        void test03() {
            assertFalse(userService.update(null));
        }

        @Test
        @DisplayName("Should return UserIdIsNullException if user id is null")
        void test04() {
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
        void test01() {
            assertTrue(userService.updateSkills(skills, userEntity.getId().toString()));
        }

        @Test
        @DisplayName("Should return false if skills is null")
        void test02() {
            assertFalse(userService.updateSkills(null, id.toString()));
        }

        @Test
        @DisplayName("Should return UserIdIsInvalidException if user id is null")
        void test03() {
            Assertions.assertThrows(UserIdIsInvalidException.class, () -> {
                userService.updateSkills(skills, null);
            });
        }

        @Test
        @DisplayName("Should return UserDoesNotExistException if the user id has no match in the database")
        void test04() {
            Exception thrown = Assertions.assertThrows(UserDoesNotExistException.class, () -> {
                userService.updateSkills(skills, id.toString());
            });

            Assertions.assertEquals("User id '7be9b195-6c72-4ea3-a51b-01b8fada2e4c' does not exist",
                    thrown.getMessage());
        }
    }

}
