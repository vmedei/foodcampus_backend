package com.ps.foodcampus.domain.model;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "setores")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String sigla;
    
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;
    
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;
    
    private String descricao;
    private String endereco;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean ativo = true;
    
    @Column(name = "data_criacao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AgendamentoVendedor> agendamentos;
} 