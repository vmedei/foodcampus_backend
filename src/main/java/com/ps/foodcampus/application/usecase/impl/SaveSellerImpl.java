package com.ps.foodcampus.application.usecase.impl;

import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.utils.ValidateDataPersonUtils;
import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.application.usecase.SaveSellerUseCase;
import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.domain.mapper.SellerDomainMapper;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SaveSellerImpl implements SaveSellerUseCase {
    private SellerDomainMapper sellerMapper;
    private SellerRepository sellerRepository;

    public SaveSellerImpl(SellerDomainMapper sellerMapper, SellerRepository sellerRepository) {
        this.sellerMapper = sellerMapper;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public SellerDTO execute(CreateSellerDTO sellerDTO, User user) throws AlreadyExistsException, InvalidDataException {
        try {
            if (!ValidateDataPersonUtils.isValidCPF(sellerDTO.getCpf())) {
                throw new InvalidDataException("CPF");
            }
            if (sellerRepository.existsByCnpjOrCpf(sellerDTO.getCnpj(), sellerDTO.getCpf())) {
                throw new AlreadyExistsException("Seller");
            }

            Seller seller = sellerMapper.fromCreateDTO(sellerDTO);
            seller.setDataCadastro(new Date());
            seller.setUser(user);
            seller.setStoreCode(getStoreCode(seller.getFantasyName()));
            sellerRepository.saveSeller(seller);

            return sellerMapper.toDTO(seller);

        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            log.error("Error while saving seller {}", e.getMessage());
            throw e;
        }
    }

    public String getStoreCode(String fantasyName) {
        String formattedFantasyName = fantasyName.trim().toLowerCase()
                .replaceAll("\\s+", "_");
        if (sellerRepository.existsByStoreCode(formattedFantasyName)) {
            int count = 1;
            String newStoreCode;
            do {
                newStoreCode = formattedFantasyName + "_" + count++;
            } while (sellerRepository.existsByStoreCode(newStoreCode));
            return newStoreCode;
        } else {
            return formattedFantasyName;
        }
    }
}
