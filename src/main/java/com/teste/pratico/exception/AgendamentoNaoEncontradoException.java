package com.teste.pratico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgendamentoNaoEncontradoException extends RuntimeException{

    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
