package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.GrupoPessoaDTO;
import com.umbandanet.caboclopenabranca.model.GrupoPessoa;
import com.umbandanet.caboclopenabranca.service.GrupoPessoasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/grupopessoas")
public class GrupoPessoasController {
    @Autowired
    private GrupoPessoasServices grupoPessoasServices;

    @GetMapping
    public List<GrupoPessoa> getAllGrupoPessoas() {
        return grupoPessoasServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GrupoPessoa> getGrupoPessoasById(@PathVariable Long id) {
        Optional<GrupoPessoa> grupoPessoa = grupoPessoasServices.findById(id);
        return grupoPessoa;
    }
    
    @PostMapping
    public ResponseEntity<GrupoPessoa> postGrupoPessoas(@RequestBody GrupoPessoa grupoPessoa) {
        return ResponseEntity.ok(grupoPessoasServices.save(grupoPessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoPessoa> updateGrupoPessoas(@PathVariable Long id, @RequestBody GrupoPessoa grupoPessoaDetails) {
        Optional<GrupoPessoa> grupoPessoa = grupoPessoasServices.findById(id);
        if (grupoPessoa.isPresent()) {
            GrupoPessoa grupoPessoaToUpdate = grupoPessoa.get();
            grupoPessoaToUpdate.setPessoaId(grupoPessoaDetails.getPessoaId());
            grupoPessoaToUpdate.setGrupoLimpezaId(grupoPessoaDetails.getGrupoLimpezaId());
            return ResponseEntity.ok(grupoPessoasServices.save(grupoPessoaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupoPessoas(@PathVariable Long id) {
        grupoPessoasServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoas")
    public List<GrupoPessoaDTO> getGrupoPessoas() {
        return grupoPessoasServices.findGrupoPessoas();
    }
}

