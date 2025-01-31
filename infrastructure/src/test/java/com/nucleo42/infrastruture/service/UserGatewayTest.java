package com.nucleo42.infrastruture.service;

import com.nucleo42.entity.User;
import com.nucleo42.infrastruture.entity.UserEntity;
import com.nucleo42.infrastruture.mapper.UserMapper;
import com.nucleo42.infrastruture.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserGatewayTest {
    @InjectMocks
    private UserGateway sut;

    @Mock
    private UserRepository repository;

    @Mock
    private UserEntity userEntity;

    @Mock
    private User user;

    @Test
    @DisplayName("Should call UserRepository.save with correct value")
    void test01() {
        try (var mockedUserMapper = mockStatic(UserMapper.class)) {
            mockedUserMapper.when(() -> UserMapper.toEntity(user)).thenReturn(userEntity);

            sut.add(user);

            verify(repository).save(userEntity);
        }
    }

    @Test
    @DisplayName("Should call UserRepository.existsByEmail with correct value")
    void test02() {
        try (var mockedUserMapper = mockStatic(UserMapper.class)) {
            mockedUserMapper.when(() -> UserMapper.toEntity(user)).thenReturn(userEntity);

            sut.add(user);

            verify(repository).existsByEmail(userEntity.getEmail());
        }
    }
}
