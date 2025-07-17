package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.OrderRepository;
import com.ps.foodcampus.application.usecase.GetOrderBySellerUseCase;
import com.ps.foodcampus.domain.dto.OrderDTO;
import com.ps.foodcampus.domain.mapper.OrderDomainMapper;
import com.ps.foodcampus.domain.model.Seller;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderBySellerImpl implements GetOrderBySellerUseCase {
    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderDomainMapper;

    public GetOrderBySellerImpl(
            OrderRepository orderRepository,
            OrderDomainMapper orderDomainMapper
    ) {
        this.orderRepository = orderRepository;
        this.orderDomainMapper = orderDomainMapper;
    }

    @Override
    public List<OrderDTO> execute(Seller seller) {
        return orderRepository.findBySeller(seller)
                .stream()
                .map(orderDomainMapper::toDTO)
                .toList();
    }
}
