package com.teste.pratico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SolicitanteNaoEncontradoException extends RuntimeException{

    public SolicitanteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}