package com.ps.foodcampus.adapters.entity.response;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductToCustomerResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String base64Image;
    private SellerResponse seller;
    private Long schedulingId;
}
