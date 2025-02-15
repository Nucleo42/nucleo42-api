package com.nucleo42.usecaseimpl;

import java.util.UUID;

import com.nucleo42.UpdateUserProfileCase;
import com.nucleo42.entity.User;
import com.nucleo42.gateway.UpdateUserProfileGateway;

public class UpdateUserProfileCaseImpl implements UpdateUserProfileCase {

    private final UpdateUserProfileGateway updateGateway;

    public UpdateUserProfileCaseImpl(UpdateUserProfileGateway updateGateway) {
        this.updateGateway = updateGateway;
    }

    @Override
    public void update(User userUpdate, UUID id) {
        updateGateway.update(userUpdate, id);
    }
}
