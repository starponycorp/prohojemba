package com.starpony.prohojemba;

import com.starpony.prohojemba.security.JWTAuthProvider;
import com.starpony.prohojemba.security.JWTFilter;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
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
import redis.clients.jedis.JedisPooled;


@Configuration
@EnableWebSecurity
public class ApplicationConfig {
    /*
        Настройка пула подключений к Redis
     */
    @Bean
    public JedisPooled jedisPooledConnections(@Value("${redis.host}") String host, @Value("${redis.port}") int port) {
        return new JedisPooled(host, port);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(JWTUtils jwtUtils) {
        return token -> jwtUtils.extractAccessToken(token).orElseThrow(() ->
                new UsernameNotFoundException("Invalid access token"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(JWTUtils jwtUtils) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService(jwtUtils));
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return  configuration.getAuthenticationManager();
    }

    /*
        Настройка авторизации и аутентификации приложения
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JWTUtils jwtUtils) throws Exception{
        return httpSecurity.cors().and().csrf().disable().
            authorizeHttpRequests().anyRequest().authenticated().and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authenticationProvider(authenticationProvider(jwtUtils)).
                addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class).build();
    }
}
