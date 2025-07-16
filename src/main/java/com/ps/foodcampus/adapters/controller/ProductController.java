package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.adapters.entity.mapper.ProductMapper;
import com.ps.foodcampus.adapters.entity.request.CreateProductRequest;
import com.ps.foodcampus.adapters.entity.response.ProductResponse;
import com.ps.foodcampus.application.exceptions.AlreadyExistsException;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.usecase.RetrieveProductUseCase;
import com.ps.foodcampus.application.usecase.SaveProductUseCase;
import com.ps.foodcampus.application.utils.AuthenticationUtil;
import com.ps.foodcampus.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Produtos", description = "Endpoints relacionados aos produtos")
public class ProductController {

    private final ProductMapper productMapper;
    private final SaveProductUseCase saveProductUseCase;
    private final RetrieveProductUseCase retrieveProductUseCase;

    public ProductController(ProductMapper productMapper, SaveProductUseCase saveProductUseCase, RetrieveProductUseCase retrieveProductUseCase) {
        this.productMapper = productMapper;
        this.saveProductUseCase = saveProductUseCase;
        this.retrieveProductUseCase = retrieveProductUseCase;
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto",
            description = "Recebe os dados do produto, valida e salva no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Dados inválidos ou produto já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar produto")
    })
    public ResponseEntity<Map<String, ?>> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        try {
            saveProductUseCase.execute(productMapper.toDTO(createProductRequest));

            return ResponseEntity.ok().body(
                    Map.of("message", "Product created successfully",
                            "product", productMapper.toResponse(createProductRequest))
            );
        } catch (InvalidDataException | AlreadyExistsException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", exception.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Internal error while creating seller"));
        }
    }
    @Operation(summary = "Recupera produtos",
            description = "Recupera todos os produtos para o cliente ou produtos do vendedor logado ou produtos de um vendedor específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos recuperados com sucesso"),
            @ApiResponse(responseCode = "403", description = "Dados inválidos ou produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar produtos")
    })
    @GetMapping
    public ResponseEntity<Map<String, ?>> retrieveProducts(@RequestParam(required = false) String storeCode) {
        try {
            List<ProductResponse> productList;
            User loggedUser = AuthenticationUtil.getAuthenticatedUser();

            if (storeCode != null && !storeCode.isEmpty()) {
                productList = retrieveProductUseCase.findProductsBySeller(storeCode)
                        .stream()
                        .map(productMapper::fromDTO)
                        .toList();
            } else if(loggedUser != null && !Objects.isNull(loggedUser.getSeller())) {
                productList = retrieveProductUseCase.findProductsByLoggedSeller()
                        .stream()
                        .map(productMapper::fromDTO)
                        .toList();
            } else {
                productList = retrieveProductUseCase.findAllProducts()
                        .stream()
                        .map(productMapper::fromDTO)
                        .toList();
            }

            return ResponseEntity.ok().body(Map.of("products", productList));

        } catch (InvalidDataException | AlreadyExistsException | NotFoundException exception ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", exception.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Internal error while retrieving products"));
        }
    }


}
