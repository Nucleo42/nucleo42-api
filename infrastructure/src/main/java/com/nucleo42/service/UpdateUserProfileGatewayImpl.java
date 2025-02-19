package com.nucleo42.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleo42.gateway.UpdateUserProfileGateway;
import com.nucleo42.entity.User;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.repository.UserEntityRepository;

import com.nucleo42.entity.UserEntity;
import com.nucleo42.exception.UserDoesNotExistException;

@Service
public class UpdateUserProfileGatewayImpl implements UpdateUserProfileGateway {
    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public void update(User userUpdate, UUID id) {

        UserEntity userEntity = findUserById(id);
        userEntity = updateUserEntity(userEntity, userUpdate);
        userRepository.save(userEntity);

    }

    public UserEntity findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserDoesNotExistException());
    }

    private UserEntity updateUserEntity(UserEntity user, User userUpdate) {

        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(userUpdate.getPassword());
        user.setBiography(userUpdate.getBiography());
        user.setSkills(UserMapper.toSkillEntity(userUpdate.getSkills()));

        return user;
    }

}
