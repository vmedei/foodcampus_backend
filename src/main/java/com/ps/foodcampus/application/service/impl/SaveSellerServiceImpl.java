package com.ps.foodcampus.application.service.impl;

import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.service.SaveSellerService;
import com.ps.foodcampus.application.usecase.SaveSellerUseCase;
import com.ps.foodcampus.application.usecase.SaveUserUseCase;
import com.ps.foodcampus.domain.dto.CreateSellerDTO;
import com.ps.foodcampus.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class SaveSellerServiceImpl implements SaveSellerService {
    private SaveUserUseCase saveUserUseCase;
    private SaveSellerUseCase saveSellerUseCase;

    public SaveSellerServiceImpl(SaveUserUseCase saveUserUseCase, SaveSellerUseCase saveSellerUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.saveSellerUseCase = saveSellerUseCase;
    }

    @Override
    @Transactional
    public void execute(CreateSellerDTO sellerDTO) throws AlreadyExistsException, InvalidDataException {
        sellerDTO.getUser().setType("vendedor");
        User user = saveUserUseCase.execute(sellerDTO.getUser());
        saveSellerUseCase.execute(sellerDTO, user);
    }
}
