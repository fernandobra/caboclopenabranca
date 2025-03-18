package com.umbandanet.caboclopenabranca.service;

import java.util.List;
import java.util.Optional;

import com.umbandanet.caboclopenabranca.model.Pessoas;

public interface PessoasServices {
    List<Pessoas> findAll();
    Optional<Pessoas> findById(Long id);
    Pessoas save(Pessoas pessoas);
    void deleteById(Long id);
}

