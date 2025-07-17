package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.AgendamentoVendedorRepository;
import com.ps.foodcampus.adapters.repository.OrderRepository;
import com.ps.foodcampus.adapters.repository.ProductRepository;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.SaveOrderUseCase;
import com.ps.foodcampus.domain.dto.CreateOrderDTO;
import com.ps.foodcampus.domain.mapper.OrderDomainMapper;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Order;
import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SaveOrderImpl implements SaveOrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderDomainMapper;
    private final AgendamentoVendedorRepository agendamentoVendedorRepository;
    private final ProductRepository productRepository;

    public SaveOrderImpl(
            OrderRepository orderRepository,
            OrderDomainMapper orderDomainMapper,
            AgendamentoVendedorRepository agendamentoVendedorRepository,
            ProductRepository productRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderDomainMapper = orderDomainMapper;
        this.agendamentoVendedorRepository = agendamentoVendedorRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void execute(CreateOrderDTO orderDetails, User user) throws NotFoundException, InvalidDataException {
        AgendamentoVendedor scheduling = agendamentoVendedorRepository.findById(orderDetails.getSchedulingId())
                .orElseThrow(() -> new NotFoundException("Scheduling: " + orderDetails.getSchedulingId()));
        Product product = productRepository.findById(orderDetails.getProductId())
                .orElseThrow(() -> new NotFoundException("Product: " + orderDetails.getProductId()));

        if (user != null && !Objects.isNull(user.getCustomer())) {
            Order order = orderRepository.findBySchedulingAndProductAndCustomer(scheduling, product, user.getCustomer()).orElseGet(() -> {;
                Order newOrder = orderDomainMapper.toDomain(orderDetails);
                newOrder.setScheduling(scheduling);
                newOrder.setProduct(product);
                newOrder.setQuantity(0);
                newOrder.setCustomer(user.getCustomer());
                return newOrder;
            });

            order.setQuantity(order.getQuantity()+orderDetails.getQuantity());
            orderRepository.save(order);

        } else {
            throw new InvalidDataException("Body");
        }
    }
}
