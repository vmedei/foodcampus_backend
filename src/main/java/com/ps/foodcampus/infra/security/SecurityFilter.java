package com.ps.foodcampus.infra.security;

import com.ps.foodcampus.adapters.repository.UserRepository;
import com.ps.foodcampus.adapters.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenService jwtTokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = this.getToken(request);
        if (jwtToken != null) {
            String userEmail = jwtTokenService.getUserEmail(jwtToken);
            userRepository.findByEmail(userEmail).ifPresent(user -> {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String valueHeader = request.getHeader("Authorization");
        if (valueHeader != null && valueHeader.startsWith("Bearer ")) {
            return valueHeader.replace("Bearer ", "");
        }
        return null;
    }
}
