package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByCpf(String cpf);
}
