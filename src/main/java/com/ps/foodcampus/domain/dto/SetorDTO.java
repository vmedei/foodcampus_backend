package com.ps.foodcampus.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetorDTO {
    private Long id;
    private String nome;
    private String sigla;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String descricao;
    private String endereco;
    private Boolean ativo = true;
    private LocalDateTime dataCriacao;
}
