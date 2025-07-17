package com.ps.foodcampus.domain.dto;

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
    private SchedulingDTO scheduling;
    private ProductDTO product;
    private Integer quantity = 1;
    private LocalDateTime criadoEm;
}
