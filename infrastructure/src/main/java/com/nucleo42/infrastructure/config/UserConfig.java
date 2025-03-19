package com.nucleo42.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nucleo42.application.gateway.GetUserProfileByIdGateway;
import com.nucleo42.application.gateway.UpdateUserProfileGateway;
import com.nucleo42.application.gateway.UpdateUserProfileSkillGateway;
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
            GetUserProfileByIdGateway findUserProfileByIdGateway) {
        return new GetUserProfileByIdCaseImpl(findUserProfileByIdGateway);

    }

    @Bean
    public UpdateUserProfileCaseImpl updateUserProfileCaseImpl(UpdateUserProfileGateway updateUserProfileGateway) {
        return new UpdateUserProfileCaseImpl(updateUserProfileGateway);
    }

    @Bean
    public UpdateUserProfileSkillCaseImpl updateUserProfileSkillCaseImpl (UpdateUserProfileSkillGateway skillGateway) {
        return new UpdateUserProfileSkillCaseImpl(skillGateway);
    }
}
