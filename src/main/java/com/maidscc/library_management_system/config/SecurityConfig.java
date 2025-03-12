package com.maidscc.library_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all requests
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .formLogin(form -> form.disable()) // Disable login form
            .httpBasic(basic -> basic.disable()); // Disable basic auth

        return http.build();
    }
}
