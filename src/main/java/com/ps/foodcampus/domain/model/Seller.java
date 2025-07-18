package com.ps.foodcampus.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    @Column(name = "nome", nullable = false)
    private String name;
    @Column(name = "nome_fantasia", nullable = false)
    private String fantasyName;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(name = "descricao", nullable = false, unique = true)
    private String description;
    @Column(nullable = false, unique = true)
    private String cnpj;
    @Column(name = "telefone", nullable = false, unique = true)
    private String phone;
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private User user;
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    @Column(name = "codigo_loja")
    private String storeCode;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AgendamentoVendedor> schedulings;

}
