package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAgendamentoVendedorRepository extends JpaRepository<AgendamentoVendedor, Long> {
    
    @Query("SELECT a FROM AgendamentoVendedor a WHERE a.setor = :setor AND " +
           "((a.dataInicio BETWEEN :inicio AND :fim) OR (a.dataFim BETWEEN :inicio AND :fim) OR " +
           "(a.dataInicio <= :inicio AND a.dataFim >= :fim)) AND " +
           "a.status IN ('AGENDADO', 'ATIVO') ORDER BY a.dataInicio")
    List<AgendamentoVendedor> findBySetorAndDataBetween(@Param("setor") Setor setor, 
                                                        @Param("inicio") LocalDateTime inicio, 
                                                        @Param("fim") LocalDateTime fim);
    
    @Query("SELECT a FROM AgendamentoVendedor a WHERE a.vendedor = :vendedor ORDER BY a.dataInicio DESC")
    List<AgendamentoVendedor> findByVendedor(@Param("vendedor") Seller vendedor);
    
    @Query("SELECT a FROM AgendamentoVendedor a WHERE a.vendedor = :vendedor AND " +
           "((a.dataInicio BETWEEN :inicio AND :fim) OR (a.dataFim BETWEEN :inicio AND :fim) OR " +
           "(a.dataInicio <= :inicio AND a.dataFim >= :fim)) ORDER BY a.dataInicio")
    List<AgendamentoVendedor> findByVendedorAndDataBetween(@Param("vendedor") Seller vendedor, 
                                                           @Param("inicio") LocalDateTime inicio, 
                                                           @Param("fim") LocalDateTime fim);
    
    @Query("SELECT COUNT(a) > 0 FROM AgendamentoVendedor a WHERE a.vendedor = :vendedor AND " +
           "((a.dataInicio BETWEEN :inicio AND :fim) OR (a.dataFim BETWEEN :inicio AND :fim) OR " +
           "(a.dataInicio <= :inicio AND a.dataFim >= :fim)) AND " +
           "a.status IN ('AGENDADO', 'ATIVO')")
    boolean existsConflito(@Param("vendedor") Seller vendedor,
                          @Param("inicio") LocalDateTime inicio,
                          @Param("fim") LocalDateTime fim);

    @Query("SELECT a FROM AgendamentoVendedor a WHERE a.vendedor = :seller AND :currentDate BETWEEN a.dataInicio AND a.dataFim AND a.status IN ('AGENDADO', 'ATIVO')")
    Optional<AgendamentoVendedor> existsScheduling(Seller seller, LocalDateTime currentDate);
    
    @Query("SELECT a FROM AgendamentoVendedor a WHERE a.setor.id = :setorId AND " +
           "DATE(a.dataInicio) = DATE(:dataInicio) AND " +
           "a.status IN ('AGENDADO', 'ATIVO') ORDER BY a.dataInicio")
    List<AgendamentoVendedor> findBySetorAndData(@Param("setorId") Long setorId, 
                                                 @Param("dataInicio") LocalDateTime dataInicio, 
                                                 @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT a FROM AgendamentoVendedor a WHERE a.id = :id AND a.status IN ('AGENDADO', 'ATIVO')")
    Optional<AgendamentoVendedor> findByIdAndValidStatus(Long id);
} 