package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.infra.db.jpa.JpaSellerRepository;
import org.springframework.stereotype.Component;

@Component
public class SellerRepositoryJpaImpl implements SellerRepository {
    private final JpaSellerRepository jpaSellerRepository;

    public SellerRepositoryJpaImpl(JpaSellerRepository jpaSellerRepository) {
        this.jpaSellerRepository = jpaSellerRepository;
    }
    @Override
    public void saveSeller(Seller seller) {
        jpaSellerRepository.save(seller);
    }

    @Override
    public boolean existsByCnpjOrEmailOrCpf(String cnpj, String email, String cpf) {
        return false;
    }
}
