package com.teste.pratico.bean;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.domain.model.Solicitante;
import com.teste.pratico.service.AgendamentoService;
import com.teste.pratico.service.SolicitanteService;
import com.teste.pratico.service.VagasService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@SessionScope
@RequiredArgsConstructor
@Data
public class AgendamentoBean extends BaseBean {
    private final AgendamentoService agendamentoService;

    private final SolicitanteService solicitanteService;

    private final VagasService vagasService;

    private List<AgendamentoDTO> listaAgendamentos;

    private List<Solicitante> listaSolicitantes;

    private List<VagasDTO> listaVagasDisponiveis;

    private Date periodoInicio;

    private Date periodoFim;

    private boolean edicao = false;

    private AgendamentoDTO agendamentoDTO;

    private Date dataAtual = new Date();

    private boolean existeVagaDisponivel;

    @PostConstruct
    public void init() {
        periodoInicio = null;
        periodoFim = null;
        listarAgendamentos();
    }
    public void listarAgendamentos() {
        listaAgendamentos = agendamentoService.consultarAgendamentosPorPeriodo(periodoInicio, periodoFim);
    }

    public String incluirAgendamento() {
        edicao = false;
        agendamentoDTO = new AgendamentoDTO();
        listaSolicitantes = solicitanteService.consultarSolicitantes().stream().map(this::mapToSolicitanteEntity).collect(Collectors.toList());

        return "manterAgendamento.xhtml?faces-redirect=true";
    }

    public String editarAgendamento(AgendamentoDTO agendamentoDTO) {
        edicao = true;
        this.agendamentoDTO = agendamentoDTO;

        listaSolicitantes = solicitanteService.consultarSolicitantes().stream().map(this::mapToSolicitanteEntity).collect(Collectors.toList());

        return "manterAgendamento.xhtml?faces-redirect=true";
    }

    public String salvarAgendamento() {
        existeVagaDisponivel = vagasService.verificarExisteVagaDisponivel(agendamentoDTO.getData());

        if(!existeVagaDisponivel) {
            exibirMensagemError("Não há vagas disponíveis para a Data informada.");
            return "";
        }

        try {
            if(edicao) {
                agendamentoService.editarAgendamento(agendamentoDTO);
                exibirMensagemInfo("Agendamento editado com sucesso!");
            } else {
                agendamentoService.salvarAgendamento(agendamentoDTO);
                exibirMensagemInfo("Agendamento salvo com sucesso!");
            }

            edicao = false;
            listarAgendamentos();

        } catch (RuntimeException e) {
            exibirMensagemError(e.getMessage());
        }

        return "listarAgendamentos.xhtml?faces-redirect=true";
    }

    public void excluirAgendamento(AgendamentoDTO agendamentoDTO) {
        try {
            agendamentoService.excluirAgendamento(agendamentoDTO);
            exibirMensagemInfo("Agendamento excluido com sucesso!");
            listarAgendamentos();

        } catch (RuntimeException e) {
            exibirMensagemError(e.getMessage());
        }
    }

    public String retornarLista() {
        edicao = false;
        return "listarAgendamentos.xhtml?faces-redirect=true";
    }

    public void onDataInicioChange() {
        if(periodoInicio != null && periodoFim != null) {
            if (periodoInicio.compareTo(periodoFim) > 0) periodoFim = periodoInicio;
        }
    }

    private Solicitante mapToSolicitanteEntity(SolicitanteDTO solicitanteDTO) {
        Solicitante solicitante = new Solicitante();
        BeanUtils.copyProperties(solicitanteDTO, solicitante);

        return solicitante;
    }

    public String formatarVagasDisponiveis(VagasDTO vagaDisponivel) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String inicio = sdf.format(vagaDisponivel.getInicio());
        String fim = sdf.format(vagaDisponivel.getFim());

        if (inicio.compareTo(fim) == 0){
            return inicio;
        } else {
            return inicio + " até " + fim;
        }

    }

}
