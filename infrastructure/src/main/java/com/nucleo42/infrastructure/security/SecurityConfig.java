package com.nucleo42.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthMiddleware authMiddleware;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            Arrays.stream(PublicRoutes.values())
                                    .map(PublicRoutes::getRoute)
                                    .toArray(String[]::new))
                            .permitAll();

                    auth.anyRequest().authenticated();

                })
                .addFilterBefore(authMiddleware, BasicAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
