package com.nucleo42.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nucleo42.UpdateUserProfileCase;
import com.nucleo42.gateway.FindUserProfileByIdGateway;
import com.nucleo42.gateway.SaveUserProfileGateway;
import com.nucleo42.gateway.UpdateUserProfileGateway;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.usecaseimpl.FindUserProfileByIdCaseImpl;
import com.nucleo42.usecaseimpl.SaveUserProfileCaseImpl;
import com.nucleo42.usecaseimpl.UpdateUserProfileCaseImpl;

@Configuration
public class UserConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public FindUserProfileByIdCaseImpl findUserProfileByIdCaseImpl(
            FindUserProfileByIdGateway findUserProfileByIdGateway) {
        return new FindUserProfileByIdCaseImpl(findUserProfileByIdGateway);

    }

    @Bean
    public SaveUserProfileCaseImpl saveUserProfileCaseImpl(SaveUserProfileGateway saveUserProfileGateway) {
        return new SaveUserProfileCaseImpl(saveUserProfileGateway);
    }

    @Bean
    public UpdateUserProfileCaseImpl updateUserProfileCaseImpl(UpdateUserProfileGateway updateUserProfileGateway) {
        return new UpdateUserProfileCaseImpl(updateUserProfileGateway);
    }
}
