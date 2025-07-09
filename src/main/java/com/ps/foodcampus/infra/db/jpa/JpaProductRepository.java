package com.ps.foodcampus.infra.db.jpa;

import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<Product,Long> {
    boolean existsByNameAndSeller(String name, Seller seller);

    List<Product> findBySeller(Seller seller);
}
