package com.ps.foodcampus.adapters.entity.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetorSimpleResponse {
    private Long id;
    private String nome;
    private String sigla;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String endereco;
} 