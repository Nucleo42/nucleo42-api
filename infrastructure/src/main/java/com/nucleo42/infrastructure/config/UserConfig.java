package com.nucleo42.infrastructure.config;

import com.nucleo42.infrastructure.service.UserGateway;
import com.nucleo42.usecase.GetUserProfileByIdCase;
import com.nucleo42.usecase.UpdateUserProfileCase;
import com.nucleo42.usecase.UpdateUserProfileSkillsCase;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public GetUserProfileByIdCase findUserProfileByIdCase(
            IGetUserProfileByIdGateway findUserProfileByIdGateway) {
        return new GetUserProfileByIdCaseImpl(findUserProfileByIdGateway);
    }

    @Bean
    public IUpdateUserProfileSkillGateway updateUserProfileSkillsCase()
    {
        return new UserGateway();
    }

    @Bean
    public IUpdateUserProfileGateway updateUserProfileGateway()
    {
        return new UserGateway();
    }

    @Bean
    public UpdateUserProfileCase updateUserProfileCase(IUpdateUserProfileGateway updateUserProfileGateway) {
        return new UpdateUserProfileCaseImpl(updateUserProfileGateway);
    }


    @Bean()
    public UpdateUserProfileSkillsCase updateUserProfileSkillCase(@Qualifier("updateUserProfileSkillsCase") IUpdateUserProfileSkillGateway skillGateway) {
        return new UpdateUserProfileSkillCaseImpl(skillGateway);
    }
}
