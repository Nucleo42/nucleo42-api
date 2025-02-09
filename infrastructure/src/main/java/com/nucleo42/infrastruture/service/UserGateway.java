package com.nucleo42.infrastruture.service;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.entity.User;
import com.nucleo42.infrastruture.mapper.UserMapper;
import com.nucleo42.infrastruture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGateway implements AddAccountGateway, LoadUserByEmailGateway {
    @Autowired
    private UserRepository repository;

    @Override
    public Boolean add(User user) {
        var userEntity = UserMapper.toEntity(user);

        var userExists = this.repository.existsByEmail(userEntity.getEmail());
        if (userExists) {
            return false;
        }

        this.repository.save(userEntity);
        return true;
    }


    @Override
    public Optional<User> load(String email) {
        this.repository.findByEmail(email);
        return Optional.empty();
    }
}
