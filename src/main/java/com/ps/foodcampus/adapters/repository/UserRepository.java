package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {
    User saveUser(User user);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
