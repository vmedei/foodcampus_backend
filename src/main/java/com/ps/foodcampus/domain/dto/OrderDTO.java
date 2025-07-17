package com.ps.foodcampus.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Customer;
import com.ps.foodcampus.domain.model.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private CustomerDTO customer;
    private AgendamentoVendedor scheduling;
    private ProductDTO product;
    private Integer quantity = 1;
    private LocalDateTime criadoEm;
}
