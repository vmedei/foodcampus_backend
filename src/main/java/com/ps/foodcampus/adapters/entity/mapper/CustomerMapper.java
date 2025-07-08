package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.response.CustomerResponse;
import com.ps.foodcampus.domain.dto.CreateCustomerDTO;
import com.ps.foodcampus.domain.dto.CustomerDTO;
import com.ps.foodcampus.adapters.entity.request.CreateCustomerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CreateCustomerDTO toDTO(CreateCustomerRequest customer);
    CustomerResponse toResponse(CreateCustomerRequest customer);
}
