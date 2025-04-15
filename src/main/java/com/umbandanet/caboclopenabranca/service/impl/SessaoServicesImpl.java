package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.model.Sessao;
import com.umbandanet.caboclopenabranca.repository.SessaoRepository;
import com.umbandanet.caboclopenabranca.service.SessaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoServicesImpl implements SessaoServices {

    @Autowired private SessaoRepository sessaoRepository;

    @Override
    public List<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    @Override
    public Optional<Sessao> findById(Long id) {
        return sessaoRepository.findById(id);
    }

    @Override
    public Sessao save(Sessao sessao) {
      return sessaoRepository.save(sessao);
    }

    @Override
    public void deleteById(Long id) {
        sessaoRepository.deleteById(id);
    }

}
