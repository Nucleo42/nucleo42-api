package com.nucleo42.usecaseimpl;

import java.util.UUID;

import com.nucleo42.FindUserProfileByIdCase;
import com.nucleo42.entity.User;
import com.nucleo42.gateway.FindUserProfileByIdGateway;

public class FindUserProfileByIdCaseImpl implements FindUserProfileByIdCase {
    private final FindUserProfileByIdGateway findUser;

    public FindUserProfileByIdCaseImpl(FindUserProfileByIdGateway findUser) {
        this.findUser = findUser;
    }

    @Override
    public User findById(UUID userId) {
        return findUser.findUserById(userId);
    }


    
}
