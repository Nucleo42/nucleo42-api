package com.nucleo42.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nucleo42.gateway.GetUserProfileByIdGateway;
import com.nucleo42.gateway.UpdateUserProfileGateway;
import com.nucleo42.gateway.UpdateUserProfileSkillGateway;
import com.nucleo42.mapper.UserMapper;
import com.nucleo42.usecaseimpl.GetUserProfileByIdCaseImpl;
import com.nucleo42.usecaseimpl.UpdateUserProfileCaseImpl;
import com.nucleo42.usecaseimpl.UpdateUserProfileSkillCaseImpl;

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
