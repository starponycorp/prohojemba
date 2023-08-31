package com.starpony.prohojemba.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // Включение CORS и отключение CSRF
        http = http.cors().and().csrf().disable();

        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();



        http.authorizeHttpRequests().anyRequest().permitAll().and().csrf().disable();
        return http.build();
    }
}
