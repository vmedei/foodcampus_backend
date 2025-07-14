package com.ps.foodcampus.adapters.entity.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetorResponse {
    private Long id;
    private String nome;
    private String sigla;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String descricao;
    private String endereco;
    private List<VendedorAgendadoResponse> vendedores;
} 