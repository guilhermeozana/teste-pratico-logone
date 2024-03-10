package com.teste.pratico.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "AGENDAMENTO")
@Data
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    private String numero;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Solicitante solicitante;
}
