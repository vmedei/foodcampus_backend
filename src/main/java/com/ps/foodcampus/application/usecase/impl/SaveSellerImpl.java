package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.utils.ValidateDataPersonUtils;
import com.ps.foodcampus.application.utils.ValidateDataSellerUtils;
import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.application.usecase.SaveSellerUseCase;
import com.ps.foodcampus.domain.mapper.SellerDomainMapper;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SaveSellerImpl implements SaveSellerUseCase {
    private SellerDomainMapper sellerMapper;
    private SellerRepository sellerRepository;

    public SaveSellerImpl(SellerDomainMapper sellerMapper, SellerRepository sellerRepository) {
        this.sellerMapper = sellerMapper;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void execute(CreateSellerDTO sellerDTO, User user) throws AlreadyExistsException, InvalidDataException {
        try {
            if (!ValidateDataPersonUtils.isValidCPF(sellerDTO.getCpf())) {
                throw new InvalidDataException("CPF");
            }
            if(!ValidateDataSellerUtils.isValidCNPJ(sellerDTO.getCnpj())) {
                throw new InvalidDataException("CNPJ");
            }
            if (sellerRepository.existsByCnpjOrCpf(sellerDTO.getCnpj(), sellerDTO.getCpf())) {
                throw new AlreadyExistsException("Seller");
            }

            Seller seller = sellerMapper.fromCreateDTO(sellerDTO);
            seller.setDataCadastro(new Date());
            seller.setUser(user);
            sellerRepository.saveSeller(seller);

        } catch (Exception e) {
            throw new RuntimeException("Error saving seller: " + e.getMessage());
        }
    }
}
