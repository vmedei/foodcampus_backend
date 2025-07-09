package com.ps.foodcampus.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "clientes")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(name = "telefone", nullable = false, unique = true)
    private String phone;
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private User user;
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;


}
