package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.Customer;
import com.ps.foodcampus.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByCpf(String cpf);
    Optional<Customer> findByUser(User user);
}
