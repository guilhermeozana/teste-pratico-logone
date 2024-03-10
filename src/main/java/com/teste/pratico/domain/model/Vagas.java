package com.teste.pratico.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VAGAS")
@Data
public class Vagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date inicio;

    private Date fim;

    private Long quantidade;
}
