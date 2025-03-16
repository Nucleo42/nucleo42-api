package com.nucleo42.usecaseimpl;

import java.util.UUID;

import com.nucleo42.GetUserProfileByIdCase;
import com.nucleo42.entity.User;
import com.nucleo42.gateway.GetUserProfileByIdGateway;

public class GetUserProfileByIdCaseImpl implements GetUserProfileByIdCase {
    private final GetUserProfileByIdGateway findUser;

    public GetUserProfileByIdCaseImpl(GetUserProfileByIdGateway findUser) {
        this.findUser = findUser;
    }

    @Override
    public User getUserProfileById(UUID userId) {
        return findUser.getUserProfileById(userId);
    }
}
