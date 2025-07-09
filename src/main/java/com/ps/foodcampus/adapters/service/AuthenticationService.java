package com.ps.foodcampus.adapters.service;

import com.ps.foodcampus.adapters.entity.request.LoginRequest;
import com.ps.foodcampus.adapters.entity.response.AuthenticatedUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticatedUserResponse authenticate(LoginRequest loginRequest);
}
