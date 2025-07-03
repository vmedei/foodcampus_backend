package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.response.SellerResponse;
import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.adapters.entity.request.CreateSellerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    SellerDTO toSellerDTO(CreateSellerRequest seller);
    SellerResponse toSellerResponse(CreateSellerRequest seller);
}
