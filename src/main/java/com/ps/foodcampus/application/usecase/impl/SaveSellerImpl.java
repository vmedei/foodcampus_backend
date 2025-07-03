package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.entity.mapper.SellerMapper;
import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.utils.ValidateDataPersonUtils;
import com.ps.foodcampus.application.utils.ValidateDataSellerUtils;
import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.application.usecase.SaveSellerUseCase;
import com.ps.foodcampus.domain.mapper.SellerDomainMapper;
import org.springframework.stereotype.Component;

@Component
public class SaveSellerImpl implements SaveSellerUseCase {
    private SellerDomainMapper sellerMapper;
    private SellerRepository sellerRepository;

    public SaveSellerImpl(SellerDomainMapper sellerMapper, SellerRepository sellerRepository) {
        this.sellerMapper = sellerMapper;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void execute(SellerDTO sellerDTO) {
        try {
            if (!ValidateDataPersonUtils.isValidCPF(sellerDTO.getCpf())) {
                throw new InvalidDataException("CPF");
            }
            if (!ValidateDataSellerUtils.isValidEmail(sellerDTO.getUser().getEmail())) {
                throw new InvalidDataException("Email");
            }
            if(!ValidateDataSellerUtils.isValidCNPJ(sellerDTO.getCnpj())) {
                throw new InvalidDataException("CNPJ");
            }
            if (sellerRepository.existsByCnpjOrEmailOrCpf(sellerDTO.getCnpj(), sellerDTO.getUser().getEmail(), sellerDTO.getCpf())) {
                throw new AlreadyExistsException("Seller");
            }

            sellerRepository.saveSeller(sellerMapper.toSeller(sellerDTO));

        } catch (Exception e) {
            throw new RuntimeException("Error saving seller: " + e.getMessage(), e);
        }
    }
}
