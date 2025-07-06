package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.application.service.SaveSellerService;
import com.ps.foodcampus.adapters.entity.mapper.SellerMapper;
import com.ps.foodcampus.adapters.entity.request.CreateSellerRequest;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

    private final SaveSellerService saveSellerService;
    private final SellerMapper sellerMapper;

    public SellerController(SaveSellerService saveSellerService, SellerMapper sellerMapper) {
        this.saveSellerService = saveSellerService;
        this.sellerMapper = sellerMapper;
    }

    @PostMapping
    public ResponseEntity<Map<String, ?>> createSeller(@RequestBody CreateSellerRequest createSellerRequest) {
        try {
            saveSellerService.execute(sellerMapper.toDTO(createSellerRequest));

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Seller created successfully",
                            "seller", sellerMapper.toResponse(createSellerRequest))
            );
        } catch (InvalidDataException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", exception.getMessage()));

        } catch (AlreadyExistsException exception) {
            log.error("Seller already exists -> sending alert email to customer");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", exception.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Internal error while creating seller"));
        }

    }

}
