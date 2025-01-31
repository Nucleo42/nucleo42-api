package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.entity.User;
import com.nucleo42.infrastruture.mapper.UserMapper;
import com.nucleo42.infrastruture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGateway implements AddAccountGateway {
    @Autowired
    private UserRepository repository;

    @Override
    public Boolean add(User user) {
        var userEntity = UserMapper.toEntity(user);
        this.repository.save(userEntity);
        return null;
    }
}
