package com.umbandanet.caboclopenabranca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.repository.PessoasRepository;
import com.umbandanet.caboclopenabranca.service.PessoasServices;

@Service
public class PessoasServicesImpl implements PessoasServices {

    @Autowired private PessoasRepository pessoasRepository;

    @Override
    public List<Pessoas> findAll() {
        return pessoasRepository.findAll();
    }

    @Override
    public Optional<Pessoas> findById(Long id) {
        return pessoasRepository.findById(id);
    }

    @Override
    public Pessoas save(Pessoas pessoas) {
        return pessoasRepository.save(pessoas);
    }

    @Override
    public void deleteById(Long id) {
         pessoasRepository.deleteById(id);
    }

}
