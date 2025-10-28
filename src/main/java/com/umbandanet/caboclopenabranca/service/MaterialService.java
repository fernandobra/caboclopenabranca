package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.dto.MaterialDTO;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    MaterialDTO save(MaterialDTO dto);
    Optional<MaterialDTO> findById(Long id);
    List<MaterialDTO> findAll();
    MaterialDTO update(Long id, MaterialDTO dto);
    void delete(Long id);
}

