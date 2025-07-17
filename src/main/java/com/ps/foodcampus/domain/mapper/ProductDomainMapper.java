package com.ps.foodcampus.domain.mapper;

import com.ps.foodcampus.domain.dto.CreateProductDTO;
import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.domain.dto.ProductDTO;
import com.ps.foodcampus.domain.dto.ProductToCustomerDTO;
import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDomainMapper {
    Product fromCreateDTO(CreateProductDTO createProductDTO);
    ProductDTO toDTO(Product product);
    ProductToCustomerDTO toProductCustomerDTO(Product product);
}
