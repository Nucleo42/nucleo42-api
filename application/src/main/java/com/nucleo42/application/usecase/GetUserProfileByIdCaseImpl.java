package com.nucleo42.application.usecase;

import java.util.UUID;

import com.nucleo42.application.gateway.GetUserProfileByIdGateway;
import com.nucleo42.entity.User;
import com.nucleo42.usecase.GetUserProfileByIdCase;

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
