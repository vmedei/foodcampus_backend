package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.Customer;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {
    void saveCustomer(Customer customer);
    Optional<Customer> findByUser(User user);
}
