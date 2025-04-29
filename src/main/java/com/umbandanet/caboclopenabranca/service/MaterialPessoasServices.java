package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.dto.MaterialPessoaDTO;
import com.umbandanet.caboclopenabranca.model.MaterialPessoa;

import java.util.List;
import java.util.Optional;

public interface MaterialPessoasServices {
    List<MaterialPessoa> findAll();
    Optional<MaterialPessoa> findById(Long id);
    MaterialPessoa save(MaterialPessoa materialPessoa);
    void deleteById(Long id);
    List<MaterialPessoaDTO> findMaterialPessoas();
}

