package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.dto.MaterialDTO;
import com.umbandanet.caboclopenabranca.model.Material;
import com.umbandanet.caboclopenabranca.repository.MaterialRepository;
import com.umbandanet.caboclopenabranca.service.MaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository repository;

    public MaterialServiceImpl(MaterialRepository repository) {
        this.repository = repository;
    }

    private MaterialDTO toDto(Material m) {
        if (m == null) return null;
        return new MaterialDTO(m.getId(), m.getItem());
    }

    private Material toEntity(MaterialDTO dto) {
        if (dto == null) return null;
        return Material.builder()
                .id(dto.getId())
                .item(dto.getItem())
                .build();
    }

    @Override
    public MaterialDTO save(MaterialDTO dto) {
        Material saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MaterialDTO> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MaterialDTO update(Long id, MaterialDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setItem(dto.getItem());
                    Material updated = repository.save(existing);
                    return toDto(updated);
                })
                .orElseThrow(() -> new IllegalArgumentException("Material não encontrado: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

