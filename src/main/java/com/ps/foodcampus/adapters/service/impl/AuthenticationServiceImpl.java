package com.ps.foodcampus.adapters.service.impl;

import com.ps.foodcampus.adapters.entity.request.LoginRequest;
import com.ps.foodcampus.adapters.entity.response.AuthenticatedUserResponse;
import com.ps.foodcampus.adapters.entity.response.UserResponse;
import com.ps.foodcampus.adapters.repository.CustomerRepository;
import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.adapters.service.AuthenticationService;
import com.ps.foodcampus.adapters.service.JwtTokenService;
import com.ps.foodcampus.domain.model.Customer;
import com.ps.foodcampus.domain.model.Seller;
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
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;

    public AuthenticationServiceImpl(JwtTokenService tokenService, AuthenticationManager authenticationManager, 
                                   SellerRepository sellerRepository, CustomerRepository customerRepository) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.sellerRepository = sellerRepository;
        this.customerRepository = customerRepository;
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

            // Buscar nome baseado no tipo do usuário
            String userName = "";
            if (user.getType().equalsIgnoreCase("VENDEDOR")) {
                Seller seller = sellerRepository.findByUser(user).orElse(null);
                if (seller != null) {
                    userName = seller.getName();
                }
            } else if (user.getType().equalsIgnoreCase("CLIENTE")) {
                Customer customer = customerRepository.findByUser(user).orElse(null);
                if (customer != null) {
                    userName = customer.getName();
                }
            }

            return AuthenticatedUserResponse.builder()
                    .response("Usuário autenticado com sucesso")
                    .user(new UserResponse(user.getEmail(), user.getType(), userName))
                    .token(token)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
