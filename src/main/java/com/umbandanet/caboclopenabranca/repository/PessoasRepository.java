package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTOProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umbandanet.caboclopenabranca.model.Pessoas;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {
    @Query(value = "SELECT p.id AS id, p.nome AS nome, MONTHNAME(p.dataNascimento) AS mes, DAY(p.dataNascimento) AS dia " +
            "FROM pessoa p " +
            "WHERE MONTH(p.dataNascimento) IN (MONTH(CURDATE()), MONTH(CURDATE() + INTERVAL 1 MONTH)) " +
            "ORDER BY mes, dia ASC ", nativeQuery = true)
    List<PessoaAniversarioDTOProjection> findProximosAniversarios();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Pessoas p " +
            "WHERE (p.login = :login OR p.email = :login) AND p.senha = :senha")
    boolean existsByLoginAndSenha(String login, String senha);

    @Query("SELECT  p  " +
            "FROM Pessoas p " +
            "WHERE (p.login = :login OR p.email = :login) AND p.senha = :senha")
    Optional<Pessoas> validateByLoginAndSenha(String login, String senha);
}