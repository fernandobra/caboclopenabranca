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
    @Query(value = "SELECT d.id, d.data_limpeza, p.nome, gl.grupo " +
            "FROM data_limpeza d " +
            "JOIN grupo_limpeza gl ON d.grupo_limpeza_id = gl.id " +
            "JOIN grupo_pessoa gp ON gl.id = gp.grupo_limpeza_id " +
            "JOIN pessoa p ON gp.pessoa_id = p.id " +
            "WHERE d.data_limpeza >= CURRENT_DATE() " +
            "ORDER BY d.data_limpeza , p.nome ASC",
            nativeQuery = true)
    List<DataLimpezaDTOProjection> findCalendarioLimpeza();

}