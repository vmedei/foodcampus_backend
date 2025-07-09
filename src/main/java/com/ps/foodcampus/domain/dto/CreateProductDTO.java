package com.ps.foodcampus.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDTO {
    private String name;
    private String description;
    private Double price;
    private String base64Image;
}
