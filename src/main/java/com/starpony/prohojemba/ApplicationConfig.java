package com.starpony.prohojemba;

import com.starpony.prohojemba.security.JWTAuthProvider;
import com.starpony.prohojemba.security.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class ApplicationConfig {
    /*
        Настройка авторизации и аутентификации приложения
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JWTAuthProvider authProvider) throws Exception{
        return httpSecurity.cors().and().csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().

                authenticationProvider(authProvider).
                addFilterAt(new JWTFilter(), UsernamePasswordAuthenticationFilter.class).

                // Настройка прав доступа к контроллерам
                        authorizeHttpRequests().anyRequest().permitAll().and().build();
    }
}
