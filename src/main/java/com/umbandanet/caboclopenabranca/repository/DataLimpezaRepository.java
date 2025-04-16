package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.dto.DataLimpezaDTOProjection;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTOProjection;
import com.umbandanet.caboclopenabranca.model.DataLimpeza;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataLimpezaRepository extends JpaRepository<DataLimpeza, Long> {
    @Query(value = "SELECT d.id, d.dataLimpeza, p.nome, gl.grupo " +
            "FROM DataLimpeza d " +
            "JOIN Grupo_Limpeza gl ON d.grupoLimpezaId = gl.id " +
            "JOIN GrupoPessoa gp ON gl.id = gp.grupoLimpeza_id " +
            "JOIN Pessoa p ON gp.pessoa_id = p.id " +
            "WHERE d.dataLimpeza >= CURRENT_DATE() " +
            "ORDER BY d.dataLimpeza , p.nome ASC",
            nativeQuery = true)
    List<DataLimpezaDTOProjection> findCalendarioLimpeza();

}