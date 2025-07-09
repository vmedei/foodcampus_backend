package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.application.service.SaveSellerService;
import com.ps.foodcampus.adapters.entity.mapper.SellerMapper;
import com.ps.foodcampus.adapters.entity.request.CreateSellerRequest;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.domain.dto.SellerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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
@Tag(name = "Vendedores", description = "Endpoints relacionados aos vendedores")
public class SellerController {

    private final SaveSellerService saveSellerService;
    private final SellerMapper sellerMapper;

    public SellerController(SaveSellerService saveSellerService, SellerMapper sellerMapper) {
        this.saveSellerService = saveSellerService;
        this.sellerMapper = sellerMapper;
    }

    @Operation(summary = "Cria um novo vendedor",
            description = "Recebe os dados do vendedor, valida e salva no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vendedor criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Dados inválidos ou vendedor já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar vendedor")
    })
    @PostMapping
    public ResponseEntity<Map<String, ?>> createSeller(@RequestBody CreateSellerRequest createSellerRequest) {
        try {
            SellerDTO savedSeller = saveSellerService.execute(sellerMapper.toDTO(createSellerRequest));

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Seller created successfully",
                            "seller", sellerMapper.toResponse(savedSeller))
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
