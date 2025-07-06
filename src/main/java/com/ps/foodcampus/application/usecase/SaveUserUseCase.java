package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.dto.CreateUserDTO;
import com.ps.foodcampus.domain.dto.UserDTO;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public interface SaveUserUseCase {
    User execute(CreateUserDTO userDTO) throws AlreadyExistsException, InvalidDataException;
}
