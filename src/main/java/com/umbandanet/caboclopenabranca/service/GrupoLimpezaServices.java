package com.umbandanet.caboclopenabranca.service;

import java.util.List;
import java.util.Optional;

import com.umbandanet.caboclopenabranca.model.GrupoLimpeza;

public interface GrupoLimpezaServices {
    List<GrupoLimpeza> findAll();
    Optional<GrupoLimpeza> findById(Long id);
    GrupoLimpeza save(GrupoLimpeza grupoLimpeza);
    void deleteById(Long id);
}
