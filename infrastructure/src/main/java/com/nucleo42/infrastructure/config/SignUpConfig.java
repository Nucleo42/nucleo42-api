package com.nucleo42.infrastructure.config;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.Hasher;
import com.nucleo42.application.usecase.AddAccountImpl;
import com.nucleo42.infrastructure.adapter.BCryptAdapter;
import com.nucleo42.infrastructure.service.UserGateway;
import com.nucleo42.usecase.AddAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpConfig {
    @Bean
    public AddAccount addAccount(AddAccountGateway addAccountGateway, Hasher hasher) {
        return new AddAccountImpl(addAccountGateway, hasher);
    }

    @Bean
    public Hasher hasher() {
        return new BCryptAdapter();
    }

    @Bean
    public AddAccountGateway addAccountGateway() {
        return new UserGateway();
    }
}
