package com.ps.foodcampus.domain.mapper;

import com.ps.foodcampus.domain.dto.CreateUserDTO;
import com.ps.foodcampus.domain.dto.UserDTO;
import com.ps.foodcampus.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDomainMapper {
    User toEntity(UserDTO userDTO);
    User fromCreateDTO(CreateUserDTO userDTO);

}
