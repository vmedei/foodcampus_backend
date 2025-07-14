package com.ps.foodcampus.adapters.controller;

import com.ps.foodcampus.adapters.entity.request.CreateAgendamentoRequest;
import com.ps.foodcampus.adapters.entity.response.SetorResponse;
import com.ps.foodcampus.adapters.entity.response.VendedorAgendadoResponse;
import com.ps.foodcampus.application.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/setores")
@Tag(name = "Setores", description = "Endpoints para gerenciar setores e agendamentos")
public class SetorController {
    
    private final SetorService setorService;
    
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os setores ativos", 
               description = "Retorna uma lista de todos os setores ativos da universidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de setores retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<SetorResponse>> listarSetores() {
        log.info("Requisição para listar setores ativos");
        List<SetorResponse> setores = setorService.listarSetoresAtivos();
        return ResponseEntity.ok(setores);
    }
    
    @GetMapping("/{setorId}/vendedores")
    @Operation(summary = "Listar vendedores agendados em um setor",
               description = "Retorna lista de vendedores agendados em um setor específico, opcionalmente filtrados por data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vendedores retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<VendedorAgendadoResponse>> listarVendedoresPorSetor(
            @PathVariable Long setorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        log.info("Requisição para listar vendedores do setor {} na data {}", setorId, data);
        List<VendedorAgendadoResponse> vendedores = setorService.listarVendedoresPorSetor(setorId, data);
        return ResponseEntity.ok(vendedores);
    }
    
    @PostMapping("/agendamento")
    @PreAuthorize("hasRole('VENDEDOR')")
    @Operation(summary = "Agendar vendedor em um setor",
               description = "Permite que um vendedor se agende em um setor específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de agendamento inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado - apenas vendedores podem agendar"),
            @ApiResponse(responseCode = "404", description = "Setor ou vendedor não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito de horário"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<String, String>> agendarVendedor(
            @RequestBody CreateAgendamentoRequest request,
            Authentication authentication) {
        log.info("Requisição para agendar vendedor {} no setor {}", authentication.getName(), request.getSetorId());
        setorService.agendarVendedor(request, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "Agendamento realizado com sucesso!"));
    }
    
    @GetMapping("/meus-agendamentos")
    @PreAuthorize("hasRole('VENDEDOR')")
    @Operation(summary = "Listar meus agendamentos",
               description = "Retorna lista de agendamentos do vendedor logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado - apenas vendedores podem ver agendamentos"),
            @ApiResponse(responseCode = "404", description = "Vendedor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<VendedorAgendadoResponse>> meuAgendamentos(
            Authentication authentication) {
        log.info("Requisição para listar agendamentos do vendedor {}", authentication.getName());
        List<VendedorAgendadoResponse> agendamentos = setorService.listarAgendamentosVendedor(authentication.getName());
        return ResponseEntity.ok(agendamentos);
    }
} 