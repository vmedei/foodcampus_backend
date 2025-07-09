package com.ps.foodcampus.adapters.entity.response;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticatedUserResponse {
    private String response;
    private UserResponse user;
    private String token;
}
