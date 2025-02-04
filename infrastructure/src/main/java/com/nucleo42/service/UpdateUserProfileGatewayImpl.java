package com.nucleo42.service;

import org.springframework.stereotype.Service;

import com.nucleo42.gateway.UpdateUserProfileGateway;
import com.nucleo42.entity.User;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.repository.UserEntityRepository;
import com.nucleo42.infrastructure.entity.UserEntity;

@Service
public class UpdateUserProfileGatewayImpl implements UpdateUserProfileGateway{
    private final UserEntityRepository userRepository;
    private final UserMapper userMapper;

    public UpdateUserProfileGatewayImpl(UserEntityRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void update(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        userEntity.setSkills(userMapper.toSkillEntity(user.getSkills()));
        userRepository.save(userEntity);
    }
}
