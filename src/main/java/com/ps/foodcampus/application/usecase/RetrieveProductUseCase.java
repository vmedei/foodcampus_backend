package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.ProductDTO;
import com.ps.foodcampus.domain.dto.ProductToCustomerDTO;
import com.ps.foodcampus.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RetrieveProductUseCase {
    List<ProductDTO> findAllProducts();
    List<ProductDTO> findProductsBySeller(String storeCode);
    List<ProductDTO> findProductsByLoggedSeller();
    List<ProductToCustomerDTO> findAllProductsActive();
}
