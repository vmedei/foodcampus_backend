package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.domain.dto.CreateOrderDTO;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public interface SaveOrderUseCase {
    void execute(CreateOrderDTO orderDetails, User user) throws NotFoundException, InvalidDataException;
}
