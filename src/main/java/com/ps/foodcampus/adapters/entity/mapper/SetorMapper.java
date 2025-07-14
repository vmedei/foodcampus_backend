package com.ps.foodcampus.adapters.entity.mapper;

import com.ps.foodcampus.adapters.entity.response.SetorResponse;
import com.ps.foodcampus.adapters.entity.response.SetorSimpleResponse;
import com.ps.foodcampus.domain.model.Setor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetorMapper {
    
    public SetorResponse toResponse(Setor setor) {
        if (setor == null) {
            return null;
        }
        
        return SetorResponse.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .sigla(setor.getSigla())
                .latitude(setor.getLatitude())
                .longitude(setor.getLongitude())
                .descricao(setor.getDescricao())
                .endereco(setor.getEndereco())
                .build();
    }
    
    public SetorSimpleResponse toSimpleResponse(Setor setor) {
        if (setor == null) {
            return null;
        }
        
        return SetorSimpleResponse.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .sigla(setor.getSigla())
                .latitude(setor.getLatitude())
                .longitude(setor.getLongitude())
                .endereco(setor.getEndereco())
                .build();
    }
    
    public List<SetorResponse> toResponseList(List<Setor> setores) {
        if (setores == null) {
            return null;
        }
        
        return setores.stream()
                .map(this::toResponse)
                .toList();
    }
} 