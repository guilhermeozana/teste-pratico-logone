package com.teste.pratico.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SOLICITANTE")
@Data
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
