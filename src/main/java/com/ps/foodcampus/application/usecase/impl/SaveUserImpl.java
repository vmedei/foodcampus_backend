package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.UserRepository;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.usecase.SaveUserUseCase;
import com.ps.foodcampus.application.utils.ValidateDataPersonUtils;
import com.ps.foodcampus.domain.dto.CreateUserDTO;
import com.ps.foodcampus.domain.mapper.UserDomainMapper;
import com.ps.foodcampus.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SaveUserImpl implements SaveUserUseCase {
    private UserRepository userRepository;
    private UserDomainMapper userMapper;

    public SaveUserImpl(UserRepository userRepository, UserDomainMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public User execute(CreateUserDTO userDTO) throws AlreadyExistsException, InvalidDataException {
        try {
            if (!ValidateDataPersonUtils.isValidEmail(userDTO.getEmail())) {
                throw new InvalidDataException("Email");
            }
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                throw new AlreadyExistsException("User");
            }

            User user = userMapper.fromCreateDTO(userDTO);
            user.setDataCadastro(new Date());
            String passwordEncrypted = new BCryptPasswordEncoder().encode(userDTO.getPassword());
            user.setPassword(passwordEncrypted);

            return userRepository.saveUser(user);

        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            log.error("Error while save user: {}", e.getMessage());
            throw e;
        }
    }
}
