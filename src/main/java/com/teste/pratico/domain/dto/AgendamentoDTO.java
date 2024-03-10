package com.teste.pratico.domain.dto;

import com.teste.pratico.domain.model.Solicitante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private Long id;

    private Date data;

    private String numero;

    private String motivo;

    private Solicitante solicitante;
}
