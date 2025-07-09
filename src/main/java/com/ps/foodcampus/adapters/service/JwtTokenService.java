package com.ps.foodcampus.adapters.service;

import com.ps.foodcampus.domain.model.User;

import java.time.Instant;

public interface JwtTokenService {
    String createToken(User user);
    Instant generateExpirationDate();
    String getUserEmail(String token);
}
