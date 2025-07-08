package com.ps.foodcampus.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private String name;
    private String cpf;
    private String phone;
    private UserDTO user;
}
