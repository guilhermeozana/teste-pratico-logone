package com.teste.pratico.service;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.model.Agendamento;
import com.teste.pratico.exception.AgendamentoNaoEncontradoException;
import com.teste.pratico.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    public List<AgendamentoDTO> consultarAgendamentosPorPeriodo(Date inicio, Date fim) {
        return agendamentoRepository.listarAgendamentosPorPeriodo(inicio, fim);
    }

    @Transactional
    public void salvarAgendamento(AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = mapToAgendamentoEntity(agendamentoDTO);

        agendamentoRepository.saveAndFlush(agendamento);
    }

    public void editarAgendamento(AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = agendamentoRepository.findById(agendamentoDTO.getId())
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Agendamento não encontrado"));

        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        agendamentoRepository.save(agendamento);
    }

    public void excluirAgendamento(AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = agendamentoRepository.findById(agendamentoDTO.getId())
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Agendamento não encontrado"));

        agendamentoRepository.delete(agendamento);
    }

    private AgendamentoDTO mapToAgendamentoDTO(Agendamento agendamento) {
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
        BeanUtils.copyProperties(agendamento, agendamentoDTO);

        return agendamentoDTO;
    }

    private Agendamento mapToAgendamentoEntity(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoDTO, agendamento);

        return agendamento;
    }
}
