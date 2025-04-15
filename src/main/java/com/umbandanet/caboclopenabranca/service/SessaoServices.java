package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.model.Sessao;

import java.util.List;
import java.util.Optional;

public interface SessaoServices {
    List<Sessao> findAll();
    Optional<Sessao> findById(Long id);
    Sessao save(Sessao sessao);
    void deleteById(Long id);
}
