package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.MaterialPessoaDTO;
import com.umbandanet.caboclopenabranca.model.MaterialPessoa;
import com.umbandanet.caboclopenabranca.service.MaterialPessoasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/caboclopenabranca/materialPessoas")
public class MaterialPessoasController {
    @Autowired
    private MaterialPessoasServices materialPessoasServices;

    @GetMapping
    public List<MaterialPessoa> getAllMaterialPessoas() {
        return materialPessoasServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MaterialPessoa> getMaterialPessoasById(@PathVariable Long id) {
        Optional<MaterialPessoa> materialPessoa = materialPessoasServices.findById(id);
        return materialPessoa;
    }
    
    @PostMapping
    public ResponseEntity<MaterialPessoa> postMaterialPessoas(@RequestBody MaterialPessoa materialPessoa) {
        return ResponseEntity.ok(materialPessoasServices.save(materialPessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialPessoa> updateMaterialPessoas(@PathVariable Long id, @RequestBody MaterialPessoa materialPessoaDetails) {
        Optional<MaterialPessoa> materialPessoa = materialPessoasServices.findById(id);
        if (materialPessoa.isPresent()) {
            MaterialPessoa materialPessoaToUpdate = materialPessoa.get();
            materialPessoaToUpdate.setPessoa_id(materialPessoaDetails.getPessoa_id());
            materialPessoaToUpdate.setMaterial_id(materialPessoaDetails.getMaterial_id());
            return ResponseEntity.ok(materialPessoasServices.save(materialPessoaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialPessoas(@PathVariable Long id) {
        materialPessoasServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/item/medium")
    public List<MaterialPessoaDTO> getMaterialPessoas() {
        return materialPessoasServices.findMaterialPessoas();
    }
}

