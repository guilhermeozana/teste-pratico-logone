package com.teste.pratico.repository;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.model.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
}
