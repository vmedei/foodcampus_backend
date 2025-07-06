package com.ps.foodcampus.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDTO {
    private String email;
    private String password;
    private String type;
}
