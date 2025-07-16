package com.ps.foodcampus.adapters.entity.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendedorAgendadoResponse {
    private Long agendamentoId;
    private Long vendedorId;
    private String nomeFantasia;
    private String telefone;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private String observacoes;
    private String storeCode;
    private SetorSimpleResponse setor;
} 