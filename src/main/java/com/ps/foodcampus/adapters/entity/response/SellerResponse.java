package com.ps.foodcampus.adapters.entity.response;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponse {
    private String name;
    private String fantasyName;
    private UserResponse user;
}
