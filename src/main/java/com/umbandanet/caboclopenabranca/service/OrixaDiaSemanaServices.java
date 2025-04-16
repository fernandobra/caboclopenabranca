package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.model.OrixaDiaSemana;

import java.util.List;
import java.util.Optional;

public interface OrixaDiaSemanaServices {
    List<OrixaDiaSemana> findAll();
    Optional<OrixaDiaSemana> findById(Long id);
    OrixaDiaSemana save(OrixaDiaSemana orixaDiaSemana);
    void deleteById(Long id);
}
