package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.CreateCustomerDTO;
import com.ps.foodcampus.domain.dto.CustomerDTO;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public interface SaveCustomerUseCase {

    void execute(CreateCustomerDTO customerDTO, User user) throws AlreadyExistsException, InvalidDataException, DataIntegrityViolationException, IllegalArgumentException;

}
