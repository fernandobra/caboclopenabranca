package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.model.OrixaDiaSemana;
import com.umbandanet.caboclopenabranca.repository.OrixaDiaSemanaRepository;
import com.umbandanet.caboclopenabranca.service.OrixaDiaSemanaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrixaDiaSemanaServicesImpl implements OrixaDiaSemanaServices {

    @Autowired private OrixaDiaSemanaRepository orixaDiaSemanaRepository;

    @Override
    public List<OrixaDiaSemana> findAll() {
        return orixaDiaSemanaRepository.findAll();
    }

    @Override
    public Optional<OrixaDiaSemana> findById(Long id) {
        return orixaDiaSemanaRepository.findById(id);
    }

    @Override
    public OrixaDiaSemana save(OrixaDiaSemana orixaDiaSemana) {
      return orixaDiaSemanaRepository.save(orixaDiaSemana);
    }

    @Override
    public void deleteById(Long id) {
        orixaDiaSemanaRepository.deleteById(id);
    }

}
