package com.ps.foodcampus.domain.mapper;

import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.domain.model.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerDomainMapper {
    SellerDTO toSellerDTO(Seller seller);
    Seller toSeller(SellerDTO sellerDTO);
}
