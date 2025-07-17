package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.entity.response.ProductToCustomerResponse;
import com.ps.foodcampus.adapters.repository.AgendamentoVendedorRepository;
import com.ps.foodcampus.adapters.repository.ProductRepository;
import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.RetrieveProductUseCase;
import com.ps.foodcampus.application.utils.AuthenticationUtil;
import com.ps.foodcampus.domain.dto.ProductDTO;
import com.ps.foodcampus.domain.dto.ProductToCustomerDTO;
import com.ps.foodcampus.domain.mapper.ProductDomainMapper;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Product;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RetrieveProductImpl implements RetrieveProductUseCase {
    private final ProductRepository productRepository;
    private final ProductDomainMapper productDomainMapper;
    private final SellerRepository sellerRepository;
    private final AgendamentoVendedorRepository agendamentoVendedorRepository;

    public RetrieveProductImpl(ProductRepository productRepository, ProductDomainMapper productDomainMapper, SellerRepository sellerRepository, AgendamentoVendedorRepository agendamentoVendedorRepository) {
        this.productRepository = productRepository;
        this.productDomainMapper = productDomainMapper;
        this.sellerRepository = sellerRepository;
        this.agendamentoVendedorRepository = agendamentoVendedorRepository;
    }
    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productDomainMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductToCustomerDTO> findAllProductsActive() {
         List<Product> products = productRepository.findAll();
         List<ProductToCustomerDTO> productDTOS = new ArrayList<>();
         for (Product product : products) {
             AgendamentoVendedor agendamentoVendedor = agendamentoVendedorRepository.existsScheduling(product.getSeller(), LocalDateTime.now()).orElse(null);
             if (!Objects.isNull(agendamentoVendedor)) {
                 ProductToCustomerDTO productDTO = productDomainMapper.toProductCustomerDTO(product);
                 productDTO.setSchedulingId(agendamentoVendedor.getId());
                 productDTOS.add(productDTO);
             }
         }

         return productDTOS;
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

    @Override
    public List<ProductDTO> findProductsByLoggedSeller() {
        User loggedUser = AuthenticationUtil.getAuthenticatedUser();
        if (loggedUser == null || loggedUser.getSeller() == null) {
            return List.of(); // Retorna lista vazia se não houver vendedor logado
        }

        return productRepository.findBySeller(loggedUser.getSeller())
                .stream()
                .map(productDomainMapper::toDTO)
                .toList();
    }


}
