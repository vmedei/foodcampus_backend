package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.request.CreateOrderRequest;
import com.ps.foodcampus.domain.dto.CreateOrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    CreateOrderDTO toOrderDTO(CreateOrderRequest createOrderRequest);
}
