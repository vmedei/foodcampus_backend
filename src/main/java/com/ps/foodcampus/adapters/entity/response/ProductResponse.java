package com.ps.foodcampus.adapters.entity.response;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private Double price;
    private String base64Image;
}
