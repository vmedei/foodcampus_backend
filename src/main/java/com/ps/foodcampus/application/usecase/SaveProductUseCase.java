package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.adapters.entity.request.CreateProductRequest;
import org.springframework.stereotype.Component;

@Component
public interface SaveProductUseCase {
    void execute(CreateProductRequest createProductRequest);
}
