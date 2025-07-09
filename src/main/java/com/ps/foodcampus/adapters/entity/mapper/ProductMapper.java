package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.request.CreateProductRequest;
import com.ps.foodcampus.adapters.entity.response.ProductResponse;
import com.ps.foodcampus.domain.dto.CreateProductDTO;
import com.ps.foodcampus.domain.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CreateProductDTO toDTO(CreateProductRequest product);
    ProductResponse toResponse(CreateProductRequest product);
    ProductResponse fromDTO(ProductDTO productDTO);
}
