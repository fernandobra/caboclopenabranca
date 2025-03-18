package com.umbandanet.caboclopenabranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umbandanet.caboclopenabranca.model.GrupoLimpeza;

@Repository // Certifique-se de que esta anotação está presente
public interface GrupoLimpezaRepository extends JpaRepository<GrupoLimpeza, Long> {
}