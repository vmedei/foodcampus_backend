package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.OrderRepository;
import com.ps.foodcampus.application.usecase.GetOrderByCustomerUseCase;
import com.ps.foodcampus.domain.dto.OrderDTO;
import com.ps.foodcampus.domain.mapper.OrderDomainMapper;
import com.ps.foodcampus.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetOrderByCustomerImpl implements GetOrderByCustomerUseCase {
    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderDomainMapper;

    public GetOrderByCustomerImpl(
            OrderRepository orderRepository,
            OrderDomainMapper orderDomainMapper
    ) {
        this.orderRepository = orderRepository;
        this.orderDomainMapper = orderDomainMapper;
    }

    @Override
    public List<OrderDTO> execute(Customer customer) {
        return orderRepository.findByCustomer(customer)
                .stream()
                .map(orderDomainMapper::toDTO)
                .toList();
    }
}
