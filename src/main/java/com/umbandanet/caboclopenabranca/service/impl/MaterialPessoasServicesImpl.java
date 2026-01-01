package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.dto.MaterialPessoaDTO;
import com.umbandanet.caboclopenabranca.model.MaterialPessoa;
import com.umbandanet.caboclopenabranca.repository.MaterialPessoasRepository;
import com.umbandanet.caboclopenabranca.service.MaterialPessoasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialPessoasServicesImpl implements MaterialPessoasServices {

    @Autowired private MaterialPessoasRepository materialPessoasRepository;

    @Override
    public List<MaterialPessoa> findAll() {
        return materialPessoasRepository.findAll();
    }

    @Override
    public Optional<MaterialPessoa> findById(Long id) {
        return materialPessoasRepository.findById(id);
    }

    @Override
    public MaterialPessoa save(MaterialPessoa materialPessoa) {
        if(materialPessoa.getId() == 0) {
            materialPessoa.setId(null);
        }
        return materialPessoasRepository.save(materialPessoa);
    }

    @Override
    public void deleteById(Long id) {
        materialPessoasRepository.deleteById(id);
    }

    @Override
    public List<MaterialPessoaDTO> findMaterialPessoas() {
        return materialPessoasRepository.findMaterialPessoas();
    }
}
