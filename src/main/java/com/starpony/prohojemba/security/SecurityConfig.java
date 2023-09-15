package com.starpony.prohojemba.security;

import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTFilter jwtFilter;
    private final JWTUtils jwtUtils;

    @Autowired
    public SecurityConfig(JWTFilter jwtFilter, JWTUtils jwtUtils) {
        this.jwtFilter = jwtFilter;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return token ->
                jwtUtils.extractAccessToken(token).orElseThrow(() -> new UsernameNotFoundException("Invalid access token"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        ProviderManager providerManager = new ProviderManager();
//        return providerManager;
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.cors().and().csrf().disable().httpBasic().disable().formLogin().disable().logout().disable().
                authorizeHttpRequests().anyRequest().authenticated().and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authenticationProvider(authenticationProvider()).
                addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
