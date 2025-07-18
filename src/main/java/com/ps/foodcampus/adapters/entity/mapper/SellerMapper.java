package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.response.SellerResponse;
import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.adapters.entity.request.CreateSellerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    CreateSellerDTO toDTO(CreateSellerRequest seller);
    SellerResponse toResponse(CreateSellerRequest seller);
    SellerResponse toResponse(SellerDTO sellerDTO);
}
