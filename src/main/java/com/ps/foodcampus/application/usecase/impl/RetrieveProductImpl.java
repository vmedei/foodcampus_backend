package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.ProductRepository;
import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.RetrieveProductUseCase;
import com.ps.foodcampus.domain.dto.ProductDTO;
import com.ps.foodcampus.domain.mapper.ProductDomainMapper;
import com.ps.foodcampus.domain.model.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RetrieveProductImpl implements RetrieveProductUseCase {
    private final ProductRepository productRepository;
    private final ProductDomainMapper productDomainMapper;
    private final SellerRepository sellerRepository;

    public RetrieveProductImpl(ProductRepository productRepository, ProductDomainMapper productDomainMapper, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.productDomainMapper = productDomainMapper;
        this.sellerRepository = sellerRepository;
    }
    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productDomainMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsBySeller(String storeCode) throws NotFoundException {
        Seller seller = sellerRepository.findByStoreCode(storeCode)
                .orElseThrow(() -> new NotFoundException("Seller"));

        return productRepository.findBySeller(seller)
                .stream()
                .map(productDomainMapper::toDTO)
                .toList();
    }


}
