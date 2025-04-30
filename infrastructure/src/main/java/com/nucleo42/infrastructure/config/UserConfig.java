package com.nucleo42.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nucleo42.application.gateway.IGetUserProfileByIdGateway;
import com.nucleo42.application.gateway.IUpdateUserProfileGateway;
import com.nucleo42.application.gateway.IUpdateUserProfileSkillGateway;
import com.nucleo42.application.usecase.GetUserProfileByIdCaseImpl;
import com.nucleo42.application.usecase.UpdateUserProfileCaseImpl;
import com.nucleo42.application.usecase.UpdateUserProfileSkillCaseImpl;
import com.nucleo42.infrastructure.mapper.UserMapper;

@Configuration
public class UserConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public GetUserProfileByIdCaseImpl findUserProfileByIdCaseImpl(
            IGetUserProfileByIdGateway findUserProfileByIdGateway) {
        return new GetUserProfileByIdCaseImpl(findUserProfileByIdGateway);

    }

    @Bean
    public UpdateUserProfileCaseImpl updateUserProfileCaseImpl(IUpdateUserProfileGateway updateUserProfileGateway) {
        return new UpdateUserProfileCaseImpl(updateUserProfileGateway);
    }

    @Bean
    public UpdateUserProfileSkillCaseImpl updateUserProfileSkillCaseImpl (IUpdateUserProfileSkillGateway skillGateway) {
        return new UpdateUserProfileSkillCaseImpl(skillGateway);
    }
}
