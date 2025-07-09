package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.application.service.SaveCustomerService;
import com.ps.foodcampus.adapters.entity.mapper.CustomerMapper;
import com.ps.foodcampus.adapters.entity.request.CreateCustomerRequest;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/customers")
@Tag(name = "Clientes", description = "Endpoins relacionados aos clientes")
public class CustomerController {

    private final SaveCustomerService saveCustomerService;
    private final CustomerMapper customerMapper;

    public CustomerController(SaveCustomerService saveCustomerService, CustomerMapper customerMapper) {
        this.saveCustomerService = saveCustomerService;
        this.customerMapper = customerMapper;
    }

    @Operation(summary = "Cria um novo cliente",
            description = "Recebe os dados do cliente, valida e salva no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Dados inválidos ou cliente já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar cliente")
    })
    @PostMapping
    public ResponseEntity<Map<String, ?>> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        try {
            saveCustomerService.execute(customerMapper.toDTO(createCustomerRequest));

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Customer created successfully",
                            "customer", customerMapper.toResponse(createCustomerRequest))
            );
        } catch (InvalidDataException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", exception.getMessage()));

        } catch (AlreadyExistsException exception) {
            log.error("Customer already exists -> sending alert email to customer");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", exception.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Internal error while creating customer"));
        }

    }

}
