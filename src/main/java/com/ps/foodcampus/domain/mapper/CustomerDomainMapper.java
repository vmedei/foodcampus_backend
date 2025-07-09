package com.ps.foodcampus.domain.mapper;

import com.ps.foodcampus.domain.dto.CreateCustomerDTO;
import com.ps.foodcampus.domain.dto.CustomerDTO;
import com.ps.foodcampus.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDomainMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
    Customer fromCreateDTO(CreateCustomerDTO customerDTO);
}
