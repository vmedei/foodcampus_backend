package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSetorRepository extends JpaRepository<Setor, Long> {
    
    @Query("SELECT s FROM Setor s WHERE s.ativo = true ORDER BY s.sigla")
    List<Setor> findAllAtivos();
    
    @Query("SELECT s FROM Setor s ORDER BY s.sigla")
    List<Setor> findAllOrderBySigla();
} 