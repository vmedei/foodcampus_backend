 package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.CustomerRepository;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.utils.ValidateDataPersonUtils;
import com.ps.foodcampus.domain.dto.CreateCustomerDTO;
import com.ps.foodcampus.application.usecase.SaveCustomerUseCase;
import com.ps.foodcampus.domain.mapper.CustomerDomainMapper;
import com.ps.foodcampus.domain.model.Customer;
import com.ps.foodcampus.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SaveCustomerImpl implements SaveCustomerUseCase {
    private CustomerDomainMapper customerMapper;
    private CustomerRepository customerRepository;

    public SaveCustomerImpl(CustomerDomainMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public void execute(CreateCustomerDTO customerDTO, User user) throws AlreadyExistsException, InvalidDataException {
        try {
            if (!ValidateDataPersonUtils.isValidCPF(customerDTO.getCpf())) {
                throw new InvalidDataException("CPF");
            }

            Customer customer = customerMapper.fromCreateDTO(customerDTO);
            customer.setDataCadastro(new Date());
            customer.setUser(user);
            customerRepository.saveCustomer(customer);

        } catch (IllegalArgumentException e) {
            log.error("Error while saving customer {}", e.getMessage());
            throw e;
        }
    }
}
