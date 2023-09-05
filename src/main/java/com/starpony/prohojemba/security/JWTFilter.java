package com.starpony.prohojemba.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;


public class JWTFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //  Поиск токена в заголовках запроса
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String bearerHeader = request.getHeader("Authorization");
        String jwtToken;

        if (bearerHeader == null || !bearerHeader.startsWith("Bearer ")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        jwtToken = bearerHeader.substring(7);

        SecurityContextHolder.getContext().setAuthentication(new JWTAuthentication(jwtToken, null, null));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
