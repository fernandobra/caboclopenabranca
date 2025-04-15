package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umbandanet.caboclopenabranca.model.GrupoLimpeza;

import java.util.List;

@Repository // Certifique-se de que esta anotação está presente
public interface GrupoLimpezaRepository extends JpaRepository<GrupoLimpeza, Long> {
}