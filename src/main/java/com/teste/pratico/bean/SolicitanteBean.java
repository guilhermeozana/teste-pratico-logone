package com.teste.pratico.bean;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.service.SolicitanteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;

@ManagedBean
@SessionScope
@RequiredArgsConstructor
@Data
public class SolicitanteBean extends BaseBean {

    private final SolicitanteService solicitanteService;

    private List<SolicitanteDTO> listaSolicitantes;

    private boolean edicao = false;

    private SolicitanteDTO solicitanteDTO;

    @PostConstruct
    public void init() {
        listarSolicitantes();
    }
    public void listarSolicitantes() {
        listaSolicitantes = solicitanteService.consultarSolicitantes();
    }

    public String incluirSolicitante() {
        edicao = false;
        solicitanteDTO = new SolicitanteDTO();

        listaSolicitantes = solicitanteService.consultarSolicitantes();

        return "manterSolicitante.xhtml?faces-redirect=true";
    }

    public String editarSolicitante(SolicitanteDTO solicitanteDTO) {
        edicao = true;
        this.solicitanteDTO = solicitanteDTO;

        listaSolicitantes = solicitanteService.consultarSolicitantes();

        return "manterSolicitante.xhtml?faces-redirect=true";
    }

    public String salvarSolicitante() {
        try {
            if(edicao) {
                solicitanteService.editarSolicitante(solicitanteDTO);
                exibirMensagemInfo("Solicitante editado com sucesso!");
            } else {
                solicitanteService.salvarSolicitante(solicitanteDTO);
                exibirMensagemInfo("Solicitante salvo com sucesso!");
            }

            edicao = false;
            listarSolicitantes();
        } catch (RuntimeException e) {
            exibirMensagemError(e.getMessage());
        }

        return "listarSolicitantes.xhtml?faces-redirect=true";
    }

    public void excluirSolicitante(SolicitanteDTO solicitanteDTO) {
        try {
            solicitanteService.excluirSolicitante(solicitanteDTO);
            exibirMensagemInfo("Solicitante excluido com sucesso!");
            listarSolicitantes();
        } catch (RuntimeException e) {
            exibirMensagemError(e.getMessage());
        }
    }

    public String retornarLista() {
        edicao = false;
        return "listarSolicitantes.xhtml?faces-redirect=true";
    }

}
