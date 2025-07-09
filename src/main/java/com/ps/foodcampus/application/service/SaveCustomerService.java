package com.ps.foodcampus.application.service;

import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.dto.CreateCustomerDTO;
import com.ps.foodcampus.domain.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public interface SaveCustomerService {
    void execute(CreateCustomerDTO customerDTO) throws AlreadyExistsException, InvalidDataException;
}
