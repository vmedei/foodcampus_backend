package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.Setor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface AgendamentoVendedorRepository {
    void save(AgendamentoVendedor agendamento);
    Optional<AgendamentoVendedor> findById(Long id);
    List<AgendamentoVendedor> findBySetorAndDataBetween(Setor setor, LocalDateTime inicio, LocalDateTime fim);
    List<AgendamentoVendedor> findByVendedor(Seller vendedor);
    List<AgendamentoVendedor> findByVendedorAndDataBetween(Seller vendedor, LocalDateTime inicio, LocalDateTime fim);
    boolean existsConflito(Seller vendedor, LocalDateTime inicio, LocalDateTime fim);
    List<AgendamentoVendedor> findBySetorAndData(Long setorId, LocalDateTime dataInicio, LocalDateTime dataFim);
} 