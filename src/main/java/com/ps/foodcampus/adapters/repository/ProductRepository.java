package com.ps.foodcampus.adapters.repository;

import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductRepository {
    void save(Product product);
    boolean existsByNameAndSeller(String name, Seller seller);
    List<Product> findAll();
    List<Product> findBySeller(Seller seller);
    void deleteById(Long id);
    Optional<Product> findById(Long id);
}
