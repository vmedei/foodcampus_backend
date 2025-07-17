package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.OrderRepository;
import com.ps.foodcampus.domain.model.*;
import com.ps.foodcampus.infra.db.jpa.JpaOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public void save(Order order) {
        jpaOrderRepository.save(order);
    }

    @Override
    public List<Order> findBySeller(Seller seller) {
        return jpaOrderRepository.findBySeller(seller);
    }

    @Override
    public List<Order> findBySchedulingId(Long schedulingId) {
        return jpaOrderRepository.findBySchedulingId(schedulingId);
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return jpaOrderRepository.findByCustomer(customer);
    }

    @Override
    public Optional<Order> findBySchedulingAndProductAndCustomer(AgendamentoVendedor agendamentoVendedor, Product product, Customer customer) {
        return jpaOrderRepository.findBySchedulingAndProductAndCustomer(agendamentoVendedor, product, customer);
    }
}
