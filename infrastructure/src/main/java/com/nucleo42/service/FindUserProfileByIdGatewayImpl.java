package com.nucleo42.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nucleo42.gateway.FindUserProfileByIdGateway;
import com.nucleo42.entity.User;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.repository.UserEntityRepository;
import com.nucleo42.infrastructure.entity.UserEntity;

@Service
public class FindUserProfileByIdGatewayImpl implements FindUserProfileByIdGateway {
    private final UserEntityRepository userRepository;
    private final UserMapper userMapper;

    public FindUserProfileByIdGatewayImpl(UserEntityRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findUserById(UUID id) {
        UserEntity userEntity = userRepository.findById(id);
        User user = userMapper.toUser(userEntity);
        user.setSkills(userMapper.toSkill(userEntity.getSkills()));
        return user;
    }

}
