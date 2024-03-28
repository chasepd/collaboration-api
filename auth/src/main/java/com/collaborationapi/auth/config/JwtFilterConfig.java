package com.collaborationapi.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.collaborationapi.auth.filter.JwtRequestFilter;

@Configuration
public class JwtFilterConfig {

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }
}
