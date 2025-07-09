package com.ps.foodcampus.adapters.repository.impl.jpa;

import com.ps.foodcampus.adapters.repository.ProductRepository;
import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.infra.db.jpa.JpaProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public void save(Product product) {
        jpaProductRepository.save(product);
    }

    @Override
    public boolean existsByNameAndSeller(String name, Seller seller) {
        return jpaProductRepository.existsByNameAndSeller(name, seller);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll();
    }

    @Override
    public List<Product> findBySeller(Seller seller) {
        return jpaProductRepository.findBySeller(seller);
    }
}
