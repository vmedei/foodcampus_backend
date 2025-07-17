package com.ps.foodcampus.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "produtos")
@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Seller seller;
    @Column(name = "nome", nullable = false)
    private String name;
    @Column(name = "descricao", nullable = false)
    private String description;
    @Column(name = "preco", nullable = false)
    private Double price;
    @Lob
    @Column(name = "base_64_image", nullable = false, columnDefinition = "LONGTEXT")
    private String base64Image;
    @Column(name = "ativo", nullable = false)
    private boolean active;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
}
