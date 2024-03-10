package com.teste.pratico.repository;

import com.teste.pratico.domain.dto.VagasDTO;
import com.teste.pratico.domain.model.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

    @Query(value = "SELECT new com.teste.pratico.domain.dto.VagasDTO(va.id, va.inicio, va.fim, va.quantidade) "
            + " FROM Vagas va"
            + " WHERE (:data BETWEEN va.inicio AND va.fim) ")
    List<VagasDTO> listarVagasPorAgendamento(@Param("data") Date data);

    @Query(value = "SELECT new com.teste.pratico.domain.dto.VagasDTO(va.id, va.inicio, va.fim, va.quantidade) "
            + " FROM Vagas va"
            + " WHERE 1 = 1 "
            + " AND (:inicio BETWEEN va.inicio AND va.fim OR :fim BETWEEN va.inicio AND va.fim)")
    List<VagasDTO> listarVagasPorPeriodo(@Param("inicio") Date inicio, @Param("fim") Date fim);
}
