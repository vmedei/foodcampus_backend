package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public interface SellerRepository {
    void saveSeller(Seller seller);
    boolean existsByCnpjOrCpf(String cnpj, String cpf);
}
