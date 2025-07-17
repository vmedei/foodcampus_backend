package com.ps.foodcampus.domain.mapper;

import com.ps.foodcampus.domain.dto.CreateOrderDTO;
import com.ps.foodcampus.domain.dto.OrderDTO;
import com.ps.foodcampus.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {
    Order toDomain(CreateOrderDTO orderDTO);
    OrderDTO toDTO(Order order);

}
