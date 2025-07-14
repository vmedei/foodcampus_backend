package com.ps.foodcampus.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ps.foodcampus.domain.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "agendamentos_vendedor")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoVendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Seller vendedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;
    
    @Column(name = "data_inicio", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicio;
    
    @Column(name = "data_fim", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFim;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private StatusAgendamento status = StatusAgendamento.AGENDADO;
    
    private String observacoes;
    
    @Column(name = "criado_em", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;
    
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;
} 