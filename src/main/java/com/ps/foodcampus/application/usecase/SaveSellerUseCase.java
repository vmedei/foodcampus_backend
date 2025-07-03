package com.ps.foodcampus.application.usecase;

import com.ps.foodcampus.domain.dto.SellerDTO;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public interface SaveSellerUseCase {

    void execute(SellerDTO sellerDTO) throws AlreadyExistsException, InvalidDataException;

}
