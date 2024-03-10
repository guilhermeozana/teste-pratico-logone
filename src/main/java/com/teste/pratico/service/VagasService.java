package com.teste.pratico.service;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.domain.model.Vagas;
import com.teste.pratico.exception.VagaNaoEncontradaException;
import com.teste.pratico.repository.VagasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VagasService {

    private final VagasRepository vagasRepository;

    private final AgendamentoService agendamentoService;

    public List<VagasDTO> consultarVagas() {
        List<Vagas> listaVagas = vagasRepository.findAll();

        return listaVagas
                .stream()
                .map(this::mapToVagasDTO)
                .collect(Collectors.toList());
    }

    public void salvarVaga(VagasDTO vagasDTO){
        Vagas vagas = mapToVagasEntity(vagasDTO);

        vagasRepository.save(vagas);
    }

    public void editarVaga(VagasDTO vagasDTO){
        Vagas vagas = vagasRepository.findById(vagasDTO.getId())
                .orElseThrow(() -> new VagaNaoEncontradaException("Vaga não encontrada."));

        BeanUtils.copyProperties(vagasDTO, vagas);

        vagasRepository.save(vagas);
    }

    public void excluirVaga(VagasDTO vagasDTO){
        Vagas vagas = vagasRepository.findById(vagasDTO.getId())
                .orElseThrow(() -> new VagaNaoEncontradaException("Vaga não encontrada."));

        vagasRepository.delete(vagas);
    }

    public boolean verificarExisteVagaDisponivel(Date data) {
        List<VagasDTO> listaVagas = vagasRepository.listarVagasPorAgendamento(data);

        if(!listaVagas.isEmpty()) {
            List<AgendamentoDTO> listaAgendamentos = agendamentoService.consultarAgendamentosPorPeriodo(listaVagas.get(0).getInicio(), listaVagas.get(0).getFim());

            return listaAgendamentos.size() < listaVagas.get(0).getQuantidade();
        }

        return false;

    }

    public boolean verificarExisteVagaPeriodo(VagasDTO vagasDTO) {
        List<VagasDTO> listaVagas = vagasRepository.listarVagasPorPeriodo(vagasDTO.getInicio(), vagasDTO.getFim());

        return !listaVagas.isEmpty();
    }
;
    private VagasDTO mapToVagasDTO(Vagas vagas) {
        VagasDTO vagasDTO = new VagasDTO();
        BeanUtils.copyProperties(vagas, vagasDTO);

        return vagasDTO;
    }

    private Vagas mapToVagasEntity(VagasDTO vagasDTO) {
        Vagas vagas = new Vagas();
        BeanUtils.copyProperties(vagasDTO, vagas);

        return vagas;
    }
}
