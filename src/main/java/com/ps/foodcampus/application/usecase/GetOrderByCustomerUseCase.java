package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.OrderDTO;
import com.ps.foodcampus.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GetOrderByCustomerUseCase {
    List<OrderDTO> execute(Customer customer);
}
