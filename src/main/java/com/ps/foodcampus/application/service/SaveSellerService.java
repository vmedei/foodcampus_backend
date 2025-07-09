package com.ps.foodcampus.application.service;

import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.domain.dto.SellerDTO;
import org.springframework.stereotype.Component;

@Component
public interface SaveSellerService {
    SellerDTO execute(CreateSellerDTO sellerDTO) throws AlreadyExistsException, InvalidDataException;
}
