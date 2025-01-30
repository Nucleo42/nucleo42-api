package com.nucleo42.usecaseimpl;

import java.util.List;
import java.util.UUID;

import com.nucleo42.UserUseCase;
import com.nucleo42.entity.User;
import com.nucleo42.gateway.UserGateway;

public class UserUseCaseImpl implements UserUseCase {
    private final UserGateway userGateway;

    public UserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void save(User user) {
        userGateway.saveUser(user);
    }

    @Override
    public void create(User user) {
       userGateway.saveUser(user); 
    }

    @Override
    public User findUserById(UUID userId) {
        return userGateway.findUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userGateway.getAllUsers();
    }

}
