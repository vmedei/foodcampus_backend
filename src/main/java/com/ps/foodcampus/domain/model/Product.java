package com.ps.foodcampus.domain.model;

import jakarta.persistence.*;
import lombok.*;

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

}
