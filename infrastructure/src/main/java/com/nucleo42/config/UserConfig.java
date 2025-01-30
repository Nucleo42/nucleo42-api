package com.nucleo42.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nucleo42.gateway.UserGateway;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.usecaseimpl.UserUseCaseImpl;

@Configuration
public class UserConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserUseCaseImpl userUseCaseImpl(UserGateway userGateway) {
        return new UserUseCaseImpl(userGateway);
    }
}
