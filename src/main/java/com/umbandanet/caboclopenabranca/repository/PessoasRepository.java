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
    @Query(value = "SELECT p.id AS id, p.nome AS nome, MONTHNAME(p.data_nascimento) AS mes, DAY(p.data_nascimento) AS dia " +
            "FROM pessoa p " +
            "WHERE p.data_nascimento is not null " +
            "ORDER BY MONTH(p.data_nascimento), DAY(p.data_nascimento) ASC ", nativeQuery = true)
    List<PessoaAniversarioDTOProjection> findProximosAniversarios();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN 1 ELSE 0 END " +
            "FROM Pessoas p " +
            "WHERE (p.login = :login OR p.email = :login)")
    boolean existsByLoginAndSenha(String login, String senha);

    @Query("SELECT  p  " +
            "FROM Pessoas p " +
            "WHERE (p.login = :login OR p.email = :login) AND p.senha = :senha")
    Optional<Pessoas> validateByLoginAndSenha(String login, String senha);

    @Query(value = "SELECT COUNT(*) " +
            "FROM pessoa p " +
            "WHERE p.login LIKE CONCAT('%', :login, '%')", nativeQuery = true)
    Long existsByLogin(String login);

    @Query(value = "SELECT COUNT(*) " +
            "FROM pessoa p " +
            "WHERE LOWER(TRIM(p.email)) = LOWER(TRIM(:email)) ", nativeQuery = true)
    Long existsByEmail(String email);

    @Query("SELECT  p  " +
            "FROM Pessoas p " +
            "WHERE p.email = :login ")
    Optional<Pessoas> findByEmail(String mail);

    @Query("SELECT  p  " +
            "FROM Pessoas p " +
            "WHERE p.token = :token ")
    Optional<Pessoas> findByToken(String token);
}