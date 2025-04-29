package com.umbandanet.caboclopenabranca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.umbandanet.caboclopenabranca.model.GrupoLimpeza;
import com.umbandanet.caboclopenabranca.service.GrupoLimpezaServices;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/grupoLimpeza")
public class GrupoLimpezaController {
    @Autowired
    private GrupoLimpezaServices grupoLimpezaServices;

    @GetMapping
    public List<GrupoLimpeza> getAllGrupoLimpezas() {
        return grupoLimpezaServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GrupoLimpeza> getGrupoLimpezaById(@PathVariable Long id) {
        Optional<GrupoLimpeza> grupoLimpeza = grupoLimpezaServices.findById(id);
        return grupoLimpeza;
    }
    
    @PostMapping
    public GrupoLimpeza postGrupoLimpeza(@RequestBody GrupoLimpeza grupoLimpeza) {
        return grupoLimpezaServices.save(grupoLimpeza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoLimpeza> putGrupoLimpeza(@PathVariable Long id, @RequestBody GrupoLimpeza grupoLimpezaDetails) {
        Optional<GrupoLimpeza> grupoLimpeza = grupoLimpezaServices.findById(id);
        if (grupoLimpeza.isPresent()) {
            GrupoLimpeza grupoLimpezaToUpdate = grupoLimpeza.get();
            grupoLimpezaToUpdate.setGrupo(grupoLimpezaDetails.getGrupo());

            return ResponseEntity.ok(grupoLimpezaServices.save(grupoLimpezaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupoLimpeza(@PathVariable Long id) {
        grupoLimpezaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

