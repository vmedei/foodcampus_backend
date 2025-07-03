package com.ps.foodcampus.adapters.entity.request;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSellerRequest {
    private String name;
    private String fantasyName;
    private String cpf;
    private String cnpj;
    private String phone;
    private String description;
    private CreateUserRequest user;

}
