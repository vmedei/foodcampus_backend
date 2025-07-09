package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.adapters.entity.request.LoginRequest;
import com.ps.foodcampus.adapters.entity.response.AuthenticatedUserResponse;
import com.ps.foodcampus.adapters.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticação", description = "Único endpoint de autenticação do sistema")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Autentica usuário", description = "Recebe email e senha do usuário, valida e retorna o usuário autenticado com token JWT")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @PostMapping
    public ResponseEntity<Map<String, ?>> authenticate(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(Map.of("user", authenticationService.authenticate(loginRequest)));
    }

}
