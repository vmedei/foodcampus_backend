package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.OrderDTO;
import com.ps.foodcampus.domain.model.Seller;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GetOrderBySellerUseCase {
    List<OrderDTO> execute(Seller seller);
}
