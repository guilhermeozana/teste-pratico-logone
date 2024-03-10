package com.teste.pratico.service;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.model.Agendamento;
import com.teste.pratico.domain.model.Solicitante;
import com.teste.pratico.exception.SolicitanteNaoEncontradoException;
import com.teste.pratico.repository.SolicitanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitanteService {

    private final SolicitanteRepository solicitanteRepository;

    public List<SolicitanteDTO> consultarSolicitantes() {
        return solicitanteRepository.findAll()
                .stream()
                .map(this::mapToSolicitanteDTO)
                .collect(Collectors.toList());
    }

    public Solicitante obterSolicitantePorId(Long id){
        return solicitanteRepository.findById(id)
                .orElseThrow(() -> new SolicitanteNaoEncontradoException("Solicitante não encontrado"));
    }

    public void salvarSolicitante(SolicitanteDTO solicitanteDTO){
        Solicitante solicitante = mapToSolicitanteEntity(solicitanteDTO);

        solicitanteRepository.save(solicitante);
    }

    public void editarSolicitante(SolicitanteDTO solicitanteDTO){
        Solicitante solicitante = solicitanteRepository.findById(solicitanteDTO.getId())
                .orElseThrow(() -> new SolicitanteNaoEncontradoException("Solicitante não encontrado"));

        BeanUtils.copyProperties(solicitanteDTO, solicitante);

        solicitanteRepository.save(solicitante);
    }

    public void excluirSolicitante(SolicitanteDTO solicitanteDTO){
        Solicitante solicitante = solicitanteRepository.findById(solicitanteDTO.getId())
                .orElseThrow(() -> new SolicitanteNaoEncontradoException("Solicitante não encontrado"));

        solicitanteRepository.delete(solicitante);
    }

    private SolicitanteDTO mapToSolicitanteDTO(Solicitante solicitante) {
        SolicitanteDTO solicitanteDTO = new SolicitanteDTO();
        BeanUtils.copyProperties(solicitante, solicitanteDTO);

        return solicitanteDTO;
    }

    private Solicitante mapToSolicitanteEntity(SolicitanteDTO solicitanteDTO) {
        Solicitante solicitante = new Solicitante();
        BeanUtils.copyProperties(solicitanteDTO, solicitante);

        return solicitante;
    }
}
