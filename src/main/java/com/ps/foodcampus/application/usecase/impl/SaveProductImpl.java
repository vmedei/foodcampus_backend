package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.ProductRepository;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.usecase.SaveProductUseCase;
import com.ps.foodcampus.application.utils.AuthenticationUtil;
import com.ps.foodcampus.domain.dto.CreateProductDTO;
import com.ps.foodcampus.domain.mapper.ProductDomainMapper;
import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.Seller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SaveProductImpl implements SaveProductUseCase {

    private final ProductRepository productRepository;
    private final ProductDomainMapper productMapper;

    public SaveProductImpl(ProductRepository productRepository, ProductDomainMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void execute(CreateProductDTO createProductDTO) throws AlreadyExistsException, InvalidDataException {
        try {
            Seller authSeller = AuthenticationUtil.getAuthenticatedUser().getSeller();

            if (productRepository.existsByNameAndSeller(createProductDTO.getName(), authSeller)) {
                throw new AlreadyExistsException("Produto");
            }
            if (createProductDTO.getPrice() <= 0) {
                throw new InvalidDataException("Preço");
            }
            Product product = productMapper.fromCreateDTO(createProductDTO);
            product.setActive(true);
            product.setSeller(authSeller);

            productRepository.save(product);

        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            log.error("Error while saving product {}", e.getMessage());
            throw e;
        }
    }
}
