package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RetrieveProductUseCase {
    List<ProductDTO> findAllProducts();
    List<ProductDTO> findProductsBySeller(String storeCode);
}
