package com.starpony.prohojemba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;


@Configuration
@EnableWebSecurity
public class ApplicationSecurity {
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public ApplicationSecurity(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // Включение CORS и отключение CSRF
        return http.cors().and().csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().

                authenticationProvider(authenticationProvider).
                addFilterAt(new JWTFilter(), UsernamePasswordAuthenticationFilter.class).

                // Настройка прав доступа к контроллерам
                authorizeHttpRequests().anyRequest().permitAll().and().build();
    }
}
