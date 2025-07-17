package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.scheduling.vendedor = ?1")
    List<Order> findBySeller(Seller seller);

    List<Order> findByCustomer(Customer customer);

    @Query("select o from Order o where o.scheduling.id = ?1")
    List<Order> findBySchedulingId(Long schedulingId);

    Optional<Order> findBySchedulingAndProductAndCustomer(AgendamentoVendedor agendamentoVendedor, Product product, Customer customer);

}
