package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaSellerRepository extends JpaRepository<Seller, Long> {
    boolean existsByCnpjOrCpf(String cnpj, String cpf);
    boolean existsByStoreCode(String storeCode);
    Optional<Seller> findByStoreCode(String storeCode);
    Optional<Seller> findByUser(User user);
}
