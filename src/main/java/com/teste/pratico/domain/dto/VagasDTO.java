package com.teste.pratico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagasDTO {
    private Long id;

    private Date inicio;

    private Date fim;

    private Long quantidade;
}
