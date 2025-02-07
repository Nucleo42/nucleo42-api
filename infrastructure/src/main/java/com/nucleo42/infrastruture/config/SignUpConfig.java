package com.nucleo42.infrastruture.config;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.protocol.HasheGenerator;
import com.nucleo42.application.usecase.AddAccountImpl;
import com.nucleo42.infrastruture.adapter.BCryptAdapter;
import com.nucleo42.infrastruture.service.UserGateway;
import com.nucleo42.usecase.AddAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignUpConfig {
    @Bean
    public AddAccount addAccount(AddAccountGateway addAccountGateway, HasheGenerator hasheGenerator) {
        return new AddAccountImpl(addAccountGateway, hasheGenerator);
    }

    @Bean
    public HasheGenerator hasher() {
        return new BCryptAdapter();
    }

    @Bean
    public AddAccountGateway addAccountGateway() {
        return new UserGateway();
    }
}
