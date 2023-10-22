package com.starpony.prohojemba.security;

import com.starpony.prohojemba.enums.Permission;
import com.starpony.prohojemba.accounts.repositories.AccountsDatabaseRepository;

import com.starpony.prohojemba.security.jwt.JWTFilter;
import com.starpony.prohojemba.security.token.access.AccessTokenAuthProvider;
import com.starpony.prohojemba.security.token.refresh.RefreshTokenAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountsDatabaseRepository accountsRepository) {
        return email -> accountsRepository.getByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Account with email=%s not found", email)));
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService(null));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       AccessTokenAuthProvider accessTokenAuthProvider,
                                                       RefreshTokenAuthProvider refreshTokenAuthProvider) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.
                authenticationProvider(accessTokenAuthProvider).
                authenticationProvider(refreshTokenAuthProvider).
                authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth -> auth.
                        requestMatchers("/auth/**").permitAll().
                                requestMatchers("/error").permitAll().
                        requestMatchers(HttpMethod.POST, "/titles").hasAuthority(Permission.MANAGE_TITLES.name()).
                        requestMatchers(HttpMethod.PUT, "/titles/*").hasAuthority(Permission.MANAGE_TITLES.name()).
                        requestMatchers(HttpMethod.DELETE, "/titles/*").hasAuthority(Permission.MANAGE_TITLES.name()).
                        requestMatchers(HttpMethod.POST, "/types").hasAuthority(Permission.MANAGE_TYPES.name()).
                        requestMatchers(HttpMethod.PUT, "/types/*").hasAuthority(Permission.MANAGE_TYPES.name()).
                        requestMatchers(HttpMethod.DELETE, "types/*").hasAuthority(Permission.MANAGE_TYPES.name()).
                        anyRequest().authenticated()
                        ).
                addFilterAt(
                        new JWTFilter(authenticationManager(null, null, null)),
                        UsernamePasswordAuthenticationFilter.class).

                build();
    }
}
