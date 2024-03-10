package com.teste.pratico.bean;

import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.service.SolicitanteService;
import com.teste.pratico.service.VagasService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScope
@RequiredArgsConstructor
@Data
public class VagasBean extends BaseBean {

    private final VagasService vagasService;

    private List<VagasDTO> listaVagas;

    private boolean edicao = false;

    private VagasDTO vagasDTO;

    private Date dataAtual = new Date();

    @PostConstruct
    public void init() {
        listarVagas();
    }

    public void listarVagas() {
        listaVagas = vagasService.consultarVagas();
    }

    public String incluirVaga() {
        edicao = false;
        vagasDTO = new VagasDTO();

        listaVagas = vagasService.consultarVagas();

        return "manterVaga.xhtml?faces-redirect=true";
    }

    public String editarVaga(VagasDTO vagasDTO) {
        edicao = true;
        this.vagasDTO = vagasDTO;

        listaVagas = vagasService.consultarVagas();

        return "manterVaga.xhtml?faces-redirect=true";
    }

    public String salvarVaga() {
        boolean existeVagaPeriodo = vagasService.verificarExisteVagaPeriodo(vagasDTO);

        if(existeVagaPeriodo) {
            exibirMensagemError("Já existem Vagas salvas para todo ou parte do período informado.");
            return "";
        }

        try {
            if (edicao) {
                vagasService.editarVaga(vagasDTO);
                exibirMensagemInfo("Vaga editada com sucesso!");
            } else {
                vagasService.salvarVaga(vagasDTO);
                exibirMensagemInfo("Vaga salva com sucesso!");
            }

            edicao = false;
            listarVagas();
        } catch (RuntimeException e) {
            exibirMensagemError(e.getMessage());
        }


        return "listarVagas.xhtml?faces-redirect=true";
    }

    public void excluirVaga(VagasDTO vagasDTO) {
        try {
            vagasService.excluirVaga(vagasDTO);
            exibirMensagemInfo("Vaga excluida com sucesso!");
            listarVagas();

        } catch (RuntimeException e) {
            exibirMensagemError(e.getMessage());
        }
    }

    public String retornarLista() {
        edicao = false;
        return "listarVagas.xhtml?faces-redirect=true";
    }

    public void onDataInicioChange() {
        if(vagasDTO.getInicio() != null && vagasDTO.getFim() != null) {
            if (vagasDTO.getInicio().compareTo(vagasDTO.getFim()) > 0) {
                vagasDTO.setFim(vagasDTO.getInicio());
            }
        }
    }

    public void onDataFimChange() {
        if(vagasDTO.getInicio() != null && vagasDTO.getFim() != null) {
            if (vagasDTO.getInicio().compareTo(vagasDTO.getFim()) > 0) {
                vagasDTO.setInicio(vagasDTO.getFim());
            }
        }
    }
}