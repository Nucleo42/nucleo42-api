package com.nucleo42.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nucleo42.entity.User;
import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.gateway.UserGateway;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.repository.UserEntityRepository;

@Service
public class UserGatewayImpl implements UserGateway {
    private final UserEntityRepository userRepository;
    private final UserMapper userMapper;

    public UserGatewayImpl(UserEntityRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userRepository.save(userMapper.toUserEntity(user));
        userEntity.setSkills(userMapper.toSkillEntity(user.getSkills()));
        userRepository.save(userEntity);
    }

    @Override
    public void create(User user) {
        UserEntity userEntity = userRepository.save(userMapper.toUserEntity(user));
        userEntity.setSkills(userMapper.toSkillEntity(user.getSkills()));
        userRepository.save(userEntity);
    }

    @Override
    public User findUserById(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserDoesNotExistException());

        User userDomain = userMapper.toUser(userEntity);
        userDomain.setSkills(userMapper.toSkill(userEntity.getSkills()));
        return userDomain;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        if (userEntities.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        List<User> users = new ArrayList<>();

        for (UserEntity ue : userEntities) {
            User user = userMapper.toUser(ue);
            user.setSkills(userMapper.toSkill(ue.getSkills()));
            users.add(user);
        }

        return users;
    }

}
