package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.HashGenerator;
import com.nucleo42.entity.User;
import com.nucleo42.exception.AcceptTermsException;
import com.nucleo42.exception.UserAlreadyExistsException;
import com.nucleo42.usecase.AddAccount;

public class AddAccountImpl implements AddAccount {
    private final AddAccountGateway addAccountGateway;
    private final HashGenerator hashGenerator;

    public AddAccountImpl(AddAccountGateway addAccountGateway, HashGenerator hashGenerator) {
        this.addAccountGateway = addAccountGateway;
        this.hashGenerator = hashGenerator;
    }

    @Override
    public String add(User user) {
        if (!user.getAcceptTerms()) {
            throw new AcceptTermsException();
        }

        var hashedPassword = this.hashGenerator.hash(user.getPassword());
        user.setPassword(hashedPassword);

        var result = this.addAccountGateway.add(user);
        if (!result) {
            throw new UserAlreadyExistsException();
        }

        return "User registered successfully";
    }
}
