package com.ps.foodcampus.domain.dto;

import com.ps.foodcampus.domain.enums.StatusAgendamento;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulingDTO {
    private Long id;
    private SellerDTO vendedor;
    private SetorDTO setor;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private StatusAgendamento status;
    private String observacoes;
    private LocalDateTime criadoEm;
}
