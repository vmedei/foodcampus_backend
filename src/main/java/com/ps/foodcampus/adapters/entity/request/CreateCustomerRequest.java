package com.ps.foodcampus.adapters.entity.request;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest {
    private String name;
    private String cpf;
    private String phone;
    private CreateUserRequest user;

}
