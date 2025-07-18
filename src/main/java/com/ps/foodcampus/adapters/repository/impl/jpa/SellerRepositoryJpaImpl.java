package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import com.ps.foodcampus.infra.db.jpa.JpaSellerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public boolean existsByCnpjOrCpf(String cnpj, String cpf) {
        return jpaSellerRepository.existsByCnpjOrCpf(cnpj, cpf);
    }

    @Override
    public boolean existsByStoreCode(String storeCode) {
        return jpaSellerRepository.existsByStoreCode(storeCode);
    }

    @Override
    public Optional<Seller> findByStoreCode(String storeCode) {
        return jpaSellerRepository.findByStoreCode(storeCode);
    }

    @Override
    public Optional<Seller> findByUser(User user) {
        return jpaSellerRepository.findByUser(user);
    }

}
