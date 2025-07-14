package com.ps.foodcampus.application.service;

import com.ps.foodcampus.adapters.entity.request.CreateAgendamentoRequest;
import com.ps.foodcampus.adapters.entity.response.SetorResponse;
import com.ps.foodcampus.adapters.entity.response.VendedorAgendadoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SetorService {
    
    List<SetorResponse> listarSetoresAtivos();
    
    List<VendedorAgendadoResponse> listarVendedoresPorSetor(Long setorId, LocalDate data);
    
    void agendarVendedor(CreateAgendamentoRequest request, String userEmail);
    
    List<VendedorAgendadoResponse> listarAgendamentosVendedor(String userEmail);
    
    void atualizarStatusAgendamento(Long agendamentoId, String status, String userEmail);
} 