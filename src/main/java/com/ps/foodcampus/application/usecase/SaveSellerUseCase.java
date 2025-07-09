package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public interface SaveSellerUseCase {

    SellerDTO execute(CreateSellerDTO sellerDTO, User user) throws AlreadyExistsException, InvalidDataException, DataIntegrityViolationException, IllegalArgumentException;

}
