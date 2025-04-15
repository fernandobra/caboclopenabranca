package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.GrupoPessoaDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.model.GrupoPessoa;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.service.GrupoPessoasServices;
import com.umbandanet.caboclopenabranca.service.PessoasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

