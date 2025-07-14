package com.ps.foodcampus.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ps.foodcampus.domain.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "reservas")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Customer cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agendamento_id", nullable = false)
    private AgendamentoVendedor agendamento;
    
    @Column(name = "data_reserva", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataReserva;
    
    @Column(name = "quantidade_pessoas")
    @Builder.Default
    private Integer quantidadePessoas = 1;
    
    private String observacoes;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private StatusReserva status = StatusReserva.PENDENTE;
    
    @Column(name = "criado_em", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime criadoEm;
} 