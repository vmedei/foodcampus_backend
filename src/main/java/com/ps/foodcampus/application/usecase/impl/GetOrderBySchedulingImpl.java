package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.AgendamentoVendedorRepository;
import com.ps.foodcampus.adapters.repository.OrderRepository;
import com.ps.foodcampus.application.exceptions.ForbiddenException;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.GetOrderBySchedulingUseCase;
import com.ps.foodcampus.application.utils.AuthenticationUtil;
import com.ps.foodcampus.domain.dto.OrderDTO;
import com.ps.foodcampus.domain.mapper.OrderDomainMapper;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetOrderBySchedulingImpl implements GetOrderBySchedulingUseCase {
    private final OrderRepository orderRepository;
    private final OrderDomainMapper orderDomainMapper;
    private AgendamentoVendedorRepository agendamentoVendedorRepository;

    public GetOrderBySchedulingImpl(
            OrderRepository orderRepository,
            OrderDomainMapper orderDomainMapper,
            AgendamentoVendedorRepository agendamentoVendedorRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderDomainMapper = orderDomainMapper;
        this.agendamentoVendedorRepository = agendamentoVendedorRepository;
    }

    @Override
    public List<OrderDTO> execute(Long schedulingId) throws NotFoundException, ForbiddenException {
        AgendamentoVendedor agendamentoVendedor = agendamentoVendedorRepository.findById(schedulingId).orElse(null);
        if (agendamentoVendedor == null) {
            throw new NotFoundException("Scheduling " + schedulingId);
        }
        return orderRepository.findBySchedulingId(schedulingId)
                .stream()
                .map(orderDomainMapper::toDTO)
                .toList();
    }
}
