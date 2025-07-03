package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.Seller;
import org.springframework.stereotype.Component;

@Component
public interface SellerRepository {
    void saveSeller(Seller seller);
    boolean existsByCnpjOrEmailOrCpf(String cnpj, String email, String cpf);
}
