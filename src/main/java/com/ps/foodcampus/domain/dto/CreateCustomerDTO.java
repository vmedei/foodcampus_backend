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
public class CreateCustomerDTO {
    private String name;
    private String cpf;
    private String phone;
    private CreateUserDTO user;
}
