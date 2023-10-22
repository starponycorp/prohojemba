package com.starpony.prohojemba.security.jwt;

import com.starpony.prohojemba.security.token.access.AccessTokenAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    public JWTFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //  Поиск токена в заголовках запроса
        String bearerHeader = request.getHeader("Authorization");
        String jwtToken;

        if (bearerHeader == null || !bearerHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = bearerHeader.substring(7);

        SecurityContextHolder.getContext().setAuthentication(
                authenticationManager.authenticate(new AccessTokenAuthentication(jwtToken, null, null, null)));

        filterChain.doFilter(request, response);
    }
}
