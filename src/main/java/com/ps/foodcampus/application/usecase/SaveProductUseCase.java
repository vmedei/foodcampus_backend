package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.adapters.entity.request.CreateProductRequest;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.dto.CreateProductDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public interface SaveProductUseCase {
    void execute(CreateProductDTO createProductDTO) throws AlreadyExistsException, InvalidDataException, DataIntegrityViolationException, IllegalArgumentException;
}
