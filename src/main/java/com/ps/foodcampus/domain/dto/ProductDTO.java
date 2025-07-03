package com.ps.foodcampus.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private String fantasyName;
    private String email;
    private String cpf;
    private String cnpj;
    private String phone;
    private UserDTO user;
}
