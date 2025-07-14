package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.SetorRepository;
import com.ps.foodcampus.domain.model.Setor;
import com.ps.foodcampus.infra.db.jpa.JpaSetorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SetorRepositoryJpaImpl implements SetorRepository {
    
    private final JpaSetorRepository jpaSetorRepository;
    
    public SetorRepositoryJpaImpl(JpaSetorRepository jpaSetorRepository) {
        this.jpaSetorRepository = jpaSetorRepository;
    }
    
    @Override
    public List<Setor> findAllAtivos() {
        return jpaSetorRepository.findAllAtivos();
    }
    
    @Override
    public Optional<Setor> findById(Long id) {
        return jpaSetorRepository.findById(id);
    }
    
    @Override
    public List<Setor> findAll() {
        return jpaSetorRepository.findAllOrderBySigla();
    }
    
    @Override
    public void save(Setor setor) {
        jpaSetorRepository.save(setor);
    }
} 