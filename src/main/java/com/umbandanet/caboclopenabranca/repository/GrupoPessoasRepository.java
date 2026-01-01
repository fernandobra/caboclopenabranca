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
    @Query(value =  "SELECT gp.id AS id, p.nome AS nome, gl.grupo AS grupo " +
            "FROM grupo_pessoa gp " +
            "LEFT JOIN pessoa p ON p.id = gp.pessoa_id " +
            "LEFT JOIN grupo_limpeza gl ON gl.id = gp.grupo_limpeza_id " +
            "ORDER BY gl.grupo, p.nome ASC ",  nativeQuery = true)
    List<GrupoPessoaDTO> findGrupoPessoas();
}