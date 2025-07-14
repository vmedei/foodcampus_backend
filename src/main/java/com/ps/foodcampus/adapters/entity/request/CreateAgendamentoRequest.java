package com.ps.foodcampus.adapters.entity.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAgendamentoRequest {
    private Long setorId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String observacoes;
} 