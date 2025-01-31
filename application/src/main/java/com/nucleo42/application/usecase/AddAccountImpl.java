package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserAlreadyExistsException;
import com.nucleo42.usecase.AddAccount;

public class AddAccountImpl implements AddAccount {
    private final AddAccountGateway addAccountGateway;

    public AddAccountImpl(AddAccountGateway addAccountGateway) {
        this.addAccountGateway = addAccountGateway;
    }

    @Override
    public String add(User user) {
        var result = this.addAccountGateway.add(user);
        if (!result) {
            throw new UserAlreadyExistsException();
        }

        return "";
    }
}
