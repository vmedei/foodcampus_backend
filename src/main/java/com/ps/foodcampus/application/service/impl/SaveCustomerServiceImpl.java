package com.ps.foodcampus.application.service.impl;

import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.service.SaveCustomerService;
import com.ps.foodcampus.application.usecase.SaveCustomerUseCase;
import com.ps.foodcampus.application.usecase.SaveUserUseCase;
import com.ps.foodcampus.domain.dto.CreateCustomerDTO;
import com.ps.foodcampus.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class SaveCustomerServiceImpl implements SaveCustomerService {
    private SaveUserUseCase saveUserUseCase;
    private SaveCustomerUseCase saveCustomerUseCase;

    public SaveCustomerServiceImpl(SaveUserUseCase saveUserUseCase, SaveCustomerUseCase saveCustomerUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.saveCustomerUseCase = saveCustomerUseCase;
    }

    @Override
    @Transactional
    public void execute(CreateCustomerDTO customerDTO) throws AlreadyExistsException, InvalidDataException {
        customerDTO.getUser().setType("CLIENTE");
        User user = saveUserUseCase.execute(customerDTO.getUser());
        saveCustomerUseCase.execute(customerDTO, user);
    }
}
