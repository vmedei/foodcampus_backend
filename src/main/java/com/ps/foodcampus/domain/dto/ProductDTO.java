package com.ps.foodcampus.domain.dto;

import com.ps.foodcampus.domain.model.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private SellerDTO seller;
    private String name;
    private String description;
    private Double price;
    private String base64Image;
    private boolean active;
}
