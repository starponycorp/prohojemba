package com.starpony.prohojemba;

import com.starpony.prohojemba.security.JWTAuthProvider;
import com.starpony.prohojemba.security.JWTFilter;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDetailsService userDetailsService(JWTUtils jwtUtils) {
        return token -> jwtUtils.extractAccessToken(token).orElseThrow(() ->
                new UsernameNotFoundException("Invalid access token"));
    }

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
