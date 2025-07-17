package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.domain.dto.CreateOrderDTO;
import com.ps.foodcampus.domain.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GetOrderBySchedulingUseCase {
    List<OrderDTO> execute(Long schedulingId) throws NotFoundException;

}
