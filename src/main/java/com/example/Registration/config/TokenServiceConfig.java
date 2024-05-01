package com.example.Registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Registration.service.TokenService;
import com.example.Registration.service.impl.TokenServiceImpl;

@Configuration
public class TokenServiceConfig {
	   @Bean
	    public TokenService tokenService() {
	        return new TokenServiceImpl();
	    }
}
