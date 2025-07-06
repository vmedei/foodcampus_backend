package com.ps.foodcampus.domain.mapper;

import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.domain.model.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerDomainMapper {
    SellerDTO toDTO(Seller seller);
    Seller toEntity(SellerDTO sellerDTO);
    Seller fromCreateDTO(CreateSellerDTO sellerDTO);
}
