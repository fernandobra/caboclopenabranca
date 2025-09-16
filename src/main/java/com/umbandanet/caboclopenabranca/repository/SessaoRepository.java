package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTOProjection;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository // Certifique-se de que esta anotação está presente
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query("SELECT  s  " +
            "FROM Sessao s " +
            "WHERE s.data >= :data " +
            "ORDER BY s.data ASC" )
    List<Sessao> findAllRegister(Date data);

    List<Sessao> findAllByOrderByDataAsc();
} 