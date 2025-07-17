package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderRepository {
    void save(Order orderDTO);
    List<Order> findBySeller(Seller seller);
    List<Order> findBySchedulingId(Long schedulingId);
    List<Order> findByCustomer(Customer customer);
    Optional<Order> findBySchedulingAndProductAndCustomer(AgendamentoVendedor agendamentoVendedor, Product product, Customer customer);
}
