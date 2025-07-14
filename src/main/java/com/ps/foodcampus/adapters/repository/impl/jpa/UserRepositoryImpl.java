package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.UserRepository;
import com.ps.foodcampus.domain.model.User;
import com.ps.foodcampus.infra.db.jpa.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }
    @Override
    public User saveUser(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jpaUserRepository.findByEmail(email));
    }
}
