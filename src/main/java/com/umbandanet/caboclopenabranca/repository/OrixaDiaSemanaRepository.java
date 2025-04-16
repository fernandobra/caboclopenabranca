package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.model.OrixaDiaSemana;
import com.umbandanet.caboclopenabranca.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Certifique-se de que esta anotação está presente
public interface OrixaDiaSemanaRepository extends JpaRepository<OrixaDiaSemana, Long> {
}