package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.ProductRepository;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.DeleteProductUseCase;
import com.ps.foodcampus.application.utils.AuthenticationUtil;
import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteProductImpl implements DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Long productId) {
        User loggedUser = AuthenticationUtil.getAuthenticatedUser();
        
        if (loggedUser == null || loggedUser.getSeller() == null) {
            throw new NotFoundException("Usuário não autorizado para deletar produtos");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        // Verifica se o produto pertence ao vendedor logado
        if (!product.getSeller().getId().equals(loggedUser.getSeller().getId())) {
            throw new NotFoundException("Produto não pertence ao vendedor logado");
        }

        productRepository.deleteById(productId);
        log.info("Produto [object Object]letado com sucesso pelo vendedor {}", productId, loggedUser.getSeller().getId());
    }
} 