package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.dto.DataLimpezaDTOProjection;
import com.umbandanet.caboclopenabranca.model.DataLimpeza;
import com.umbandanet.caboclopenabranca.repository.DataLimpezaRepository;
import com.umbandanet.caboclopenabranca.service.DataLimpezaServices;
import com.umbandanet.caboclopenabranca.service.SessaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataLimpezaServicesImpl implements DataLimpezaServices {

    @Autowired private DataLimpezaRepository dataLimpezaRepository;

    @Override
    public List<DataLimpeza> findAll() {
        return dataLimpezaRepository.findAll();
    }

    @Override
    public Optional<DataLimpeza> findById(Long id) {
        return dataLimpezaRepository.findById(id);
    }

    @Override
    public DataLimpeza save(DataLimpeza dataLimpeza) {
      return dataLimpezaRepository.save(dataLimpeza);
    }

    @Override
    public void deleteById(Long id) {
        dataLimpezaRepository.deleteById(id);
    }

    @Override
    public List<DataLimpezaDTOProjection> findDataLimpeza() {
        return dataLimpezaRepository.findCalendarioLimpeza();
    }

}
