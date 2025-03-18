package com.umbandanet.caboclopenabranca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umbandanet.caboclopenabranca.model.GrupoLimpeza;
import com.umbandanet.caboclopenabranca.repository.GrupoLimpezaRepository;
import com.umbandanet.caboclopenabranca.service.GrupoLimpezaServices;

@Service
public class GrupoLimpezaServicesImpl implements GrupoLimpezaServices {

    @Autowired private GrupoLimpezaRepository grupoLimpezaRepository;

    @Override
    public List<GrupoLimpeza> findAll() {
        return grupoLimpezaRepository.findAll();
    }

    @Override
    public Optional<GrupoLimpeza> findById(Long id) {
        return grupoLimpezaRepository.findById(id);
    }

    @Override
    public GrupoLimpeza save(GrupoLimpeza grupoLimpeza) {
      return grupoLimpezaRepository.save(grupoLimpeza);
    }

    @Override
    public void deleteById(Long id) {
        grupoLimpezaRepository.deleteById(id);
    }

}
