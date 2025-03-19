package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.UpdateUserProfileGateway;
import com.nucleo42.entity.User;
import com.nucleo42.usecase.UpdateUserProfileCase;

public class UpdateUserProfileCaseImpl implements UpdateUserProfileCase {

    private final UpdateUserProfileGateway updateGateway;

    public UpdateUserProfileCaseImpl(UpdateUserProfileGateway updateGateway) {
        this.updateGateway = updateGateway;
    }

    @Override
    public void update(User userUpdate) {
        updateGateway.update(userUpdate);
    }
}
