package com.teste.pratico.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class BaseBean {

    public void exibirMensagemInfo(String mensagem) {
        addMensagem(FacesMessage.SEVERITY_INFO, "Mensagem Informativa", mensagem);
    }

    public void exibirMensagemWarn(String mensagem) {
        addMensagem(FacesMessage.SEVERITY_WARN, "Mensagem de Aviso", mensagem);
    }

    public void exibirMensagemError(String mensagem) {
        addMensagem(FacesMessage.SEVERITY_ERROR, "Mensagem de Erro", mensagem);
    }

    private void addMensagem(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
