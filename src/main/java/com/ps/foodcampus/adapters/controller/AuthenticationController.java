package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.adapters.entity.request.LoginRequest;
import com.ps.foodcampus.adapters.entity.response.AuthenticatedUserResponse;
import com.ps.foodcampus.adapters.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Map<String, ?>> authenticate(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(Map.of("user", authenticationService.authenticate(loginRequest)));
    }

}
