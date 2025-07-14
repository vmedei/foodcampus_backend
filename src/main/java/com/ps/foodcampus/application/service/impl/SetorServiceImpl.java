package com.ps.foodcampus.application.service.impl;

import com.ps.foodcampus.adapters.entity.mapper.AgendamentoVendedorMapper;
import com.ps.foodcampus.adapters.entity.mapper.SetorMapper;
import com.ps.foodcampus.adapters.entity.request.CreateAgendamentoRequest;
import com.ps.foodcampus.adapters.entity.response.SetorResponse;
import com.ps.foodcampus.adapters.entity.response.VendedorAgendadoResponse;
import com.ps.foodcampus.adapters.repository.AgendamentoVendedorRepository;
import com.ps.foodcampus.adapters.repository.SetorRepository;
import com.ps.foodcampus.adapters.repository.SellerRepository;
import com.ps.foodcampus.adapters.repository.UserRepository;
import com.ps.foodcampus.application.exceptions.InvalidDataException;
import com.ps.foodcampus.application.exceptions.NotFoundException;
import com.ps.foodcampus.application.service.SetorService;
import com.ps.foodcampus.domain.enums.StatusAgendamento;
import com.ps.foodcampus.domain.model.AgendamentoVendedor;
import com.ps.foodcampus.domain.model.Seller;
import com.ps.foodcampus.domain.model.Setor;
import com.ps.foodcampus.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class SetorServiceImpl implements SetorService {
    
    private final SetorRepository setorRepository;
    private final AgendamentoVendedorRepository agendamentoVendedorRepository;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final SetorMapper setorMapper;
    private final AgendamentoVendedorMapper agendamentoVendedorMapper;
    
    public SetorServiceImpl(SetorRepository setorRepository,
                           AgendamentoVendedorRepository agendamentoVendedorRepository,
                           UserRepository userRepository,
                           SellerRepository sellerRepository,
                           SetorMapper setorMapper,
                           AgendamentoVendedorMapper agendamentoVendedorMapper) {
        this.setorRepository = setorRepository;
        this.agendamentoVendedorRepository = agendamentoVendedorRepository;
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.setorMapper = setorMapper;
        this.agendamentoVendedorMapper = agendamentoVendedorMapper;
    }
    
    @Override
    public List<SetorResponse> listarSetoresAtivos() {
        log.info("Listando setores ativos");
        List<Setor> setores = setorRepository.findAllAtivos();
        log.info("Setores encontrados: {}", setores.size());
        
        if (!setores.isEmpty()) {
            Setor primeiro = setores.get(0);
            log.info("Primeiro setor: id={}, nome={}, sigla={}", primeiro.getId(), primeiro.getNome(), primeiro.getSigla());
        }
        
        List<SetorResponse> response = setorMapper.toResponseList(setores);
        log.info("Setores mapeados: {}", response.size());
        return response;
    }
    
    @Override
    public List<VendedorAgendadoResponse> listarVendedoresPorSetor(Long setorId, LocalDate data) {
        log.info("Listando vendedores para setor {} na data {}", setorId, data);
        
        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() -> new NotFoundException("Setor não encontrado"));
        
        LocalDateTime inicioData;
        LocalDateTime fimData;
        
        if (data != null) {
            inicioData = data.atStartOfDay();
            fimData = data.atTime(LocalTime.MAX);
        } else {
            inicioData = LocalDateTime.now();
            fimData = LocalDateTime.now().plusDays(30);
        }
        
        List<AgendamentoVendedor> agendamentos = agendamentoVendedorRepository
                .findBySetorAndDataBetween(setor, inicioData, fimData);
        
        return agendamentoVendedorMapper.toVendedorAgendadoResponseList(agendamentos);
    }
    
    @Override
    @Transactional
    public void agendarVendedor(CreateAgendamentoRequest request, String userEmail) {
        log.info("Agendando vendedor no setor {} para {}", request.getSetorId(), userEmail);
        
        // Validações
        if (request.getDataInicio().isAfter(request.getDataFim())) {
            throw new InvalidDataException("Data de início deve ser anterior à data de fim");
        }
        
        if (request.getDataInicio().isBefore(LocalDateTime.now())) {
            throw new InvalidDataException("Data de início não pode ser no passado");
        }
        
        // Buscar usuário e vendedor
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        
        Seller seller = sellerRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado"));
        
        // Buscar setor
        Setor setor = setorRepository.findById(request.getSetorId())
                .orElseThrow(() -> new NotFoundException("Setor não encontrado"));
        
        // Verificar conflitos de horário para o vendedor
        if (agendamentoVendedorRepository.existsConflito(seller, request.getDataInicio(), request.getDataFim())) {
            throw new InvalidDataException("Você já possui um agendamento conflitante nesse horário");
        }
        
        // Criar agendamento
        AgendamentoVendedor agendamento = AgendamentoVendedor.builder()
                .vendedor(seller)
                .setor(setor)
                .dataInicio(request.getDataInicio())
                .dataFim(request.getDataFim())
                .observacoes(request.getObservacoes())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .build();
        
        agendamentoVendedorRepository.save(agendamento);
        
        log.info("Agendamento criado com sucesso para vendedor {}", seller.getId());
    }
    
    @Override
    public List<VendedorAgendadoResponse> listarAgendamentosVendedor(String userEmail) {
        log.info("Listando agendamentos do vendedor {}", userEmail);
        
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        
        Seller seller = sellerRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado"));
        
        List<AgendamentoVendedor> agendamentos = agendamentoVendedorRepository.findByVendedor(seller);
        
        return agendamentoVendedorMapper.toVendedorAgendadoResponseList(agendamentos);
    }
    
    @Override
    @Transactional
    public void atualizarStatusAgendamento(Long agendamentoId, String status, String userEmail) {
        log.info("Atualizando status do agendamento {} para {} pelo usuário {}", agendamentoId, status, userEmail);
        
        // Validar status
        StatusAgendamento novoStatus;
        try {
            novoStatus = StatusAgendamento.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Status inválido: " + status);
        }
        
        // Buscar usuário e vendedor
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        
        Seller seller = sellerRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado"));
        
        // Buscar agendamento
        AgendamentoVendedor agendamento = agendamentoVendedorRepository.findById(agendamentoId)
                .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
        
        // Verificar se o agendamento pertence ao vendedor
        if (!agendamento.getVendedor().getId().equals(seller.getId())) {
            throw new InvalidDataException("Você só pode atualizar seus próprios agendamentos");
        }
        
        // Atualizar status
        agendamento.setStatus(novoStatus);
        agendamentoVendedorRepository.save(agendamento);
        
        log.info("Status do agendamento {} atualizado para {}", agendamentoId, novoStatus);
    }
} 