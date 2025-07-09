package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface SellerRepository {
    void saveSeller(Seller seller);
    boolean existsByCnpjOrCpf(String cnpj, String cpf);
    boolean existsByStoreCode(String storeCode);
    Optional<Seller> findByStoreCode(String storeCode);
}
