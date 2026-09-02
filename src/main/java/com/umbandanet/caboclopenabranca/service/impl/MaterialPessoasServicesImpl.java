package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.dto.MaterialPessoaDTO;
import com.umbandanet.caboclopenabranca.dto.MaterialPessoaLoteRequestDTO;
import com.umbandanet.caboclopenabranca.model.MaterialPessoa;
import com.umbandanet.caboclopenabranca.repository.MaterialRepository;
import com.umbandanet.caboclopenabranca.repository.MaterialPessoasRepository;
import com.umbandanet.caboclopenabranca.repository.PessoasRepository;
import com.umbandanet.caboclopenabranca.service.MaterialPessoasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MaterialPessoasServicesImpl implements MaterialPessoasServices {

    @Autowired private MaterialPessoasRepository materialPessoasRepository;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private PessoasRepository pessoasRepository;

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
    @Transactional
    public List<MaterialPessoa> saveLote(MaterialPessoaLoteRequestDTO request) {
        if (request == null || request.getMaterialId() == null) {
            throw new IllegalArgumentException("materialId é obrigatório.");
        }

        if (!materialRepository.existsById(request.getMaterialId())) {
            throw new IllegalArgumentException("Material não encontrado: " + request.getMaterialId());
        }

        List<Long> pessoaIds = request.getPessoaIds();
        if (pessoaIds == null) {
            throw new IllegalArgumentException("pessoaIds é obrigatório.");
        }

        if (pessoaIds.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("pessoaIds não pode conter valores nulos.");
        }

        List<Long> pessoaIdsUnicos = new ArrayList<>(new LinkedHashSet<>(pessoaIds));
        if (!pessoaIdsUnicos.isEmpty()) {
            long pessoasExistentes = pessoasRepository.countByIdIn(pessoaIdsUnicos);
            if (pessoasExistentes != pessoaIdsUnicos.size()) {
                throw new IllegalArgumentException("Uma ou mais pessoas não foram encontradas.");
            }
        }

        materialPessoasRepository.deleteByMaterialId(request.getMaterialId());

        if (pessoaIdsUnicos.isEmpty()) {
            return List.of();
        }

        List<MaterialPessoa> associacoes = pessoaIdsUnicos.stream()
                .map(pessoaId -> {
                    MaterialPessoa materialPessoa = new MaterialPessoa();
                    materialPessoa.setId(null);
                    materialPessoa.setMaterial_id(request.getMaterialId());
                    materialPessoa.setPessoa_id(pessoaId);
                    return materialPessoa;
                })
                .toList();

        return materialPessoasRepository.saveAll(associacoes);
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
