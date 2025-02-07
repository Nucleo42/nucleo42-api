package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.HasheGenerator;
import com.nucleo42.entity.User;
import com.nucleo42.exception.AcceptTermsException;
import com.nucleo42.exception.UserAlreadyExistsException;
import com.nucleo42.usecase.AddAccount;

public class AddAccountImpl implements AddAccount {
    private final AddAccountGateway addAccountGateway;
    private final HasheGenerator hasheGenerator;

    public AddAccountImpl(AddAccountGateway addAccountGateway, HasheGenerator hasheGenerator) {
        this.addAccountGateway = addAccountGateway;
        this.hasheGenerator = hasheGenerator;
    }

    @Override
    public String add(User user) {
        if (!user.getAcceptTerms()) {
            throw new AcceptTermsException();
        }

        var hashedPassword = this.hasheGenerator.hash(user.getPassword());
        user.setPassword(hashedPassword);

        var result = this.addAccountGateway.add(user);
        if (!result) {
            throw new UserAlreadyExistsException();
        }

        return "User registered successfully";
    }
}
