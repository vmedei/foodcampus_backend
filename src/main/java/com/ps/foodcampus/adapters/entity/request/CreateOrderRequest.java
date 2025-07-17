package com.ps.foodcampus.adapters.entity.request;

import com.ps.foodcampus.adapters.entity.response.ProductResponse;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    private Long schedulingId;
    private Long productId;
    private Integer quantity;
}
