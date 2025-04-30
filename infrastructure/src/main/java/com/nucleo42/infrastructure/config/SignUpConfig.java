package com.nucleo42.infrastructure.config;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.HashGenerator;
import com.nucleo42.application.usecase.AddAccountImpl;
import com.nucleo42.infrastructure.adapter.BCryptAdapter;
import com.nucleo42.infrastructure.service.UserGateway;
import com.nucleo42.usecase.AddAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpConfig {
    @Bean
    public AddAccount addAccount(AddAccountGateway addAccountGateway, HashGenerator hashGenerator) {
        return new AddAccountImpl(addAccountGateway, hashGenerator);
    }

    @Bean
    public HashGenerator hashGenerator() {
        return new BCryptAdapter();
    }

    @Bean
    public AddAccountGateway addAccountGateway() {
        return new UserGateway();
    }
}
