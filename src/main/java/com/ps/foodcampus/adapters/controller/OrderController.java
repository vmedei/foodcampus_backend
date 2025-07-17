package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.adapters.entity.mapper.OrderMapper;
import com.ps.foodcampus.adapters.entity.request.CreateOrderRequest;
import com.ps.foodcampus.application.exceptions.ForbiddenException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.GetOrderByCustomerUseCase;
import com.ps.foodcampus.application.usecase.GetOrderBySchedulingUseCase;
import com.ps.foodcampus.application.usecase.GetOrderBySellerUseCase;
import com.ps.foodcampus.application.usecase.SaveOrderUseCase;
import com.ps.foodcampus.application.utils.AuthenticationUtil;
import com.ps.foodcampus.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Pedidos", description = "Endpoints relacionados aos pedidos")
public class OrderController {

    private final GetOrderByCustomerUseCase getOrderByCustomerUseCase;
    private final GetOrderBySellerUseCase getOrderBySellerUseCase;
    private final GetOrderBySchedulingUseCase getOrderBySchedulingUseCase;
    private final SaveOrderUseCase saveOrderUseCase;
    private final OrderMapper orderMapper;

    public OrderController(
            GetOrderByCustomerUseCase getOrderByCustomerUseCase,
            GetOrderBySellerUseCase getOrderBySellerUseCase,
            SaveOrderUseCase saveOrderUseCase,
            OrderMapper orderMapper,
            GetOrderBySchedulingUseCase getOrderBySchedulingUseCase
    ) {
        this.getOrderByCustomerUseCase = getOrderByCustomerUseCase;
        this.getOrderBySellerUseCase = getOrderBySellerUseCase;
        this.saveOrderUseCase = saveOrderUseCase;
        this.orderMapper = orderMapper;
        this.getOrderBySchedulingUseCase = getOrderBySchedulingUseCase;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pedido",
            description = "Recebe os dados do pedido, valida e salva no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos do pedido")
    })
    public ResponseEntity<Map<String, ?>> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            User authenticatedUser = AuthenticationUtil.getAuthenticatedUser();

            saveOrderUseCase.execute(orderMapper.toOrderDTO(createOrderRequest), authenticatedUser);
            return ResponseEntity.ok(Map.of("status", true, "message", "Order created successfully"));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", false, "error", e));
        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(Map.of("status", false, "message", "Invalid order data"));
        }

    }

    @GetMapping("/customer")
    @Operation(summary = "Obtém pedidos do cliente autenticado",
            description = "Retorna todos os pedidos associados ao cliente autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar pedidos")
    })
    public ResponseEntity<Map<String, ?>> getOrdersByCustomerId() {
        try {
            User authenticatedUser = AuthenticationUtil.getAuthenticatedUser();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", false, "error", "An unexpected error occurred"));
        }
    }

    @GetMapping("/seller")
    @Operation(summary = "Obtém pedidos do vendedor autenticado",
            description = "Retorna todos os pedidos associados ao vendedor autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar pedidos")
    })
    public ResponseEntity<Map<String, ?>> getOrdersBySeller() {
        try {
            User authenticatedUser = AuthenticationUtil.getAuthenticatedUser();
            return ResponseEntity.ok(Map.of("orders", getOrderBySellerUseCase.execute(authenticatedUser.getSeller())));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", false, "error", "An unexpected error occurred"));
        }
    }

    @GetMapping("/scheduling/{schedulingId}")
    @Operation(summary = "Obtém pedidos por agendamento",
            description = "Retorna todos os pedidos associados a um agendamento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos recuperados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido ao agendamento")
    })
    public ResponseEntity<Map<String, ?>> getOrdersByScheduling(@PathVariable Long schedulingId) {
        try {
            return ResponseEntity.ok(Map.of("orders", getOrderBySchedulingUseCase.execute(schedulingId)));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", false, "error", e.getMessage()));
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("status", false, "error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", false, "error", "An unexpected error occurred"));
        }
    }

}
