package com.ps.foodcampus.adapters.service.impl;

import com.ps.foodcampus.adapters.entity.request.LoginRequest;
import com.ps.foodcampus.adapters.entity.response.AuthenticatedUserResponse;
import com.ps.foodcampus.adapters.entity.response.UserResponse;
import com.ps.foodcampus.adapters.service.AuthenticationService;
import com.ps.foodcampus.adapters.service.JwtTokenService;
import com.ps.foodcampus.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    public AuthenticationServiceImpl(JwtTokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticatedUserResponse authenticate(LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            if (!auth.isAuthenticated()) {
                return new AuthenticatedUserResponse("Falha ao autenticar usuário", null, null);
            }
            User user = (User) auth.getPrincipal();
            String token = tokenService.createToken(user);

            return AuthenticatedUserResponse.builder()
                    .response("Usuário autenticado com sucesso")
                    .user(new UserResponse(user.getEmail(), user.getType()))
                    .token(token)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
