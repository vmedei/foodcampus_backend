package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.AgendamentoVendedorRepository;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.Setor;
import com.ps.foodcampus.infra.db.jpa.JpaAgendamentoVendedorRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AgendamentoVendedorRepositoryJpaImpl implements AgendamentoVendedorRepository {
    
    private final JpaAgendamentoVendedorRepository jpaAgendamentoVendedorRepository;
    
    public AgendamentoVendedorRepositoryJpaImpl(JpaAgendamentoVendedorRepository jpaAgendamentoVendedorRepository) {
        this.jpaAgendamentoVendedorRepository = jpaAgendamentoVendedorRepository;
    }
    
    @Override
    public void save(AgendamentoVendedor agendamento) {
        jpaAgendamentoVendedorRepository.save(agendamento);
    }
    
    @Override
    public Optional<AgendamentoVendedor> findById(Long id) {
        return jpaAgendamentoVendedorRepository.findById(id);
    }
    
    @Override
    public List<AgendamentoVendedor> findBySetorAndDataBetween(Setor setor, LocalDateTime inicio, LocalDateTime fim) {
        return jpaAgendamentoVendedorRepository.findBySetorAndDataBetween(setor, inicio, fim);
    }
    
    @Override
    public List<AgendamentoVendedor> findByVendedor(Seller vendedor) {
        return jpaAgendamentoVendedorRepository.findByVendedor(vendedor);
    }
    
    @Override
    public List<AgendamentoVendedor> findByVendedorAndDataBetween(Seller vendedor, LocalDateTime inicio, LocalDateTime fim) {
        return jpaAgendamentoVendedorRepository.findByVendedorAndDataBetween(vendedor, inicio, fim);
    }
    
    @Override
    public boolean existsConflito(Seller vendedor, LocalDateTime inicio, LocalDateTime fim) {
        return jpaAgendamentoVendedorRepository.existsConflito(vendedor, inicio, fim);
    }
    
    @Override
    public List<AgendamentoVendedor> findBySetorAndData(Long setorId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return jpaAgendamentoVendedorRepository.findBySetorAndData(setorId, dataInicio, dataFim);
    }

    @Override
    public Optional<AgendamentoVendedor> existsScheduling(Seller seller, LocalDateTime currentDate) {
        return jpaAgendamentoVendedorRepository.existsScheduling(seller, currentDate);
    }
} 