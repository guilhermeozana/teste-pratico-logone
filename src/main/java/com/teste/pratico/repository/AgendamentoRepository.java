package com.teste.pratico.repository;

import com.teste.pratico.domain.dto.AgendamentoDTO;
import com.teste.pratico.domain.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = "SELECT new com.teste.pratico.domain.dto.AgendamentoDTO(ag.id, ag.data, ag.numero, ag.motivo, ag.solicitante) "
                + " FROM Agendamento ag"
                + " WHERE 1 = 1 "
                + " AND (:inicio IS NULL OR ag.data >= :inicio) "
                + " AND (:fim IS NULL OR ag.data <= :fim) ")
    List<AgendamentoDTO> listarAgendamentosPorPeriodo(@Param("inicio") Date inicio, @Param("fim") Date fim);
}
