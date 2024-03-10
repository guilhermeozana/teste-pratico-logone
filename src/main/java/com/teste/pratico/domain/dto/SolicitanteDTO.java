package com.teste.pratico.domain.dto;

import com.teste.pratico.domain.model.Solicitante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitanteDTO {
    private Long id;

    private String nome;
}
