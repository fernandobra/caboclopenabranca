package com.umbandanet.caboclopenabranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umbandanet.caboclopenabranca.model.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {

}
