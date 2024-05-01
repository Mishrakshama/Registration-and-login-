package com.example.Registration.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;
//
//
//@Configuration
//public class SecurityConfig {
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http.authorizeRequests(authorizeRequests ->
//	                authorizeRequests
//	                .mvcMatchers("/", "/register", "/verify-email", "/reset-password").permitAll()
//	                        .anyRequest().authenticated()
//	        )
//	                .formLogin(Customizer.withDefaults())
//	                .logout(logout -> logout
//	                        .logoutSuccessUrl("/").permitAll()
//	                );
//	        return http.build();
//	    }
//	
//}
