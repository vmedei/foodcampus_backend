package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.Setor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SetorRepository {
    List<Setor> findAllAtivos();
    Optional<Setor> findById(Long id);
    List<Setor> findAll();
    void save(Setor setor);
} 