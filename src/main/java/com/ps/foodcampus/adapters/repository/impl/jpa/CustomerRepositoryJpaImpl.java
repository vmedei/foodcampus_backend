package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.CustomerRepository;
import com.ps.foodcampus.domain.model.Customer;
import com.ps.foodcampus.domain.model.User;
import com.ps.foodcampus.infra.db.jpa.JpaCustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerRepositoryJpaImpl implements CustomerRepository {
    private final JpaCustomerRepository jpaCustomerRepository;

    public CustomerRepositoryJpaImpl(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }
    @Override
    public void saveCustomer(Customer customer) {
        jpaCustomerRepository.save(customer);
    }

}
