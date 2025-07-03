package com.ps.foodcampus.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "vendedores")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "nome_fantasia")
    private String fantasyName;
    private String email;
    private String cpf;
    private String cnpj;
    @Column(name = "telefone")
    private String phone;


}
