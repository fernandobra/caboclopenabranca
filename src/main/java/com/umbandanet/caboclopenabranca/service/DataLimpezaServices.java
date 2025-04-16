package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.dto.DataLimpezaDTOProjection;
import com.umbandanet.caboclopenabranca.model.DataLimpeza;
import com.umbandanet.caboclopenabranca.model.Sessao;

import java.util.List;
import java.util.Optional;

public interface DataLimpezaServices {
    List<DataLimpeza> findAll();
    Optional<DataLimpeza> findById(Long id);
    DataLimpeza save(DataLimpeza dataLimpeza);
    void deleteById(Long id);
    List<DataLimpezaDTOProjection> findDataLimpeza();
}
