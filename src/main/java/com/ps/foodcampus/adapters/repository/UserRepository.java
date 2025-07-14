package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository {
    User saveUser(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
