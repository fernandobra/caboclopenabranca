package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.dto.GrupoPessoaDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTOProjection;
import com.umbandanet.caboclopenabranca.model.GrupoPessoa;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoPessoasRepository extends JpaRepository<GrupoPessoa, Long> {
    @Query(value =  "SELECT p.nome AS nome, gl.grupo AS grupo " +
            "FROM db_caboclo.grupopessoa gp " +
            "LEFT JOIN db_caboclo.pessoa p ON p.id = gp.pessoa_id " +
            "LEFT JOIN db_caboclo.grupo_limpeza gl ON gl.id = gp.grupoLimpeza_id " +
            "ORDER BY gl.grupo, p.nome ASC ",  nativeQuery = true)
    List<GrupoPessoaDTO> findGrupoPessoas();
}