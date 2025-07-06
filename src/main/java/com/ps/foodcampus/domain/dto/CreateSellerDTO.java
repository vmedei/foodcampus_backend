package com.ps.foodcampus.domain.dto;

import com.ps.foodcampus.adapters.entity.request.CreateUserRequest;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSellerDTO {
    private String name;
    private String fantasyName;
    private String cpf;
    private String cnpj;
    private String phone;
    private String description;
    private CreateUserDTO user;
}
