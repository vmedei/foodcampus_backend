package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.response.VendedorAgendadoResponse;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgendamentoVendedorMapper {
    
    private final SetorMapper setorMapper;
    
    public AgendamentoVendedorMapper(SetorMapper setorMapper) {
        this.setorMapper = setorMapper;
    }
    
    public VendedorAgendadoResponse toVendedorAgendadoResponse(AgendamentoVendedor agendamento) {
        if (agendamento == null) {
            return null;
        }
        
        return VendedorAgendadoResponse.builder()
                .agendamentoId(agendamento.getId())
                .vendedorId(agendamento.getVendedor().getId())
                .nomeFantasia(agendamento.getVendedor().getFantasyName())
                .telefone(agendamento.getVendedor().getPhone())
                .descricao(agendamento.getVendedor().getDescription())
                .dataInicio(agendamento.getDataInicio())
                .dataFim(agendamento.getDataFim())
                .status(agendamento.getStatus().name())
                .observacoes(agendamento.getObservacoes())
                .setor(setorMapper.toSimpleResponse(agendamento.getSetor()))
                .build();
    }
    
    public List<VendedorAgendadoResponse> toVendedorAgendadoResponseList(List<AgendamentoVendedor> agendamentos) {
        if (agendamentos == null) {
            return null;
        }
        
        return agendamentos.stream()
                .map(this::toVendedorAgendadoResponse)
                .toList();
    }
} 