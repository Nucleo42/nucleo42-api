package com.nucleo42.application.usecase;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.entity.User;
import com.nucleo42.usecase.AddAccount;

public class AddAccountImpl implements AddAccount {
    private AddAccountGateway addAccountGateway;

    public AddAccountImpl(AddAccountGateway addAccountGateway) {
        this.addAccountGateway = addAccountGateway;
    }

    @Override
    public String add(User user) {
        this.addAccountGateway.add(user);
        return "";
    }
}
