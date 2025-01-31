package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.Hasher;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserAlreadyExistsException;
import com.nucleo42.usecase.AddAccount;

public class AddAccountImpl implements AddAccount {
    private final AddAccountGateway addAccountGateway;
    private final Hasher hasher;

    public AddAccountImpl(AddAccountGateway addAccountGateway, Hasher hasher) {
        this.addAccountGateway = addAccountGateway;
        this.hasher = hasher;
    }

    @Override
    public String add(User user) {
        var hashedPassword = this.hasher.hash(user.getPassword());
        user.setPassword(hashedPassword);

        var result = this.addAccountGateway.add(user);
        if (!result) {
            throw new UserAlreadyExistsException();
        }

        return "User registered successfully";
    }
}
