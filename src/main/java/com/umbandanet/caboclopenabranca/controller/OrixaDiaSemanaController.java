package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.model.OrixaDiaSemana;
import com.umbandanet.caboclopenabranca.service.OrixaDiaSemanaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/orixadiasemana")
public class OrixaDiaSemanaController {
    @Autowired
    private OrixaDiaSemanaServices orixaDiaSemanaServices;

    @GetMapping
    public List<OrixaDiaSemana> getAllOrixaDiaSemanas() {
        return orixaDiaSemanaServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<OrixaDiaSemana> getOrixaDiaSemanaById(@PathVariable Long id) {
        return orixaDiaSemanaServices.findById(id);
    }
    
    @PostMapping
    public OrixaDiaSemana postOrixaDiaSemana(@RequestBody OrixaDiaSemana orixaDiaSemana) {
        return orixaDiaSemanaServices.save(orixaDiaSemana);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrixaDiaSemana> putOrixaDiaSemana(@PathVariable Long id, @RequestBody OrixaDiaSemana orixaDiaSemanaDetails) {
        Optional<OrixaDiaSemana> OrixaDiaSemana = orixaDiaSemanaServices.findById(id);
        if (OrixaDiaSemana.isPresent()) {
            OrixaDiaSemana orixaDiaSemanaToUpdate = OrixaDiaSemana.get();
            orixaDiaSemanaToUpdate.setNome(orixaDiaSemanaDetails.getNome());
            orixaDiaSemanaToUpdate.setDia_semana(orixaDiaSemanaDetails.getDia_semana());

            return ResponseEntity.ok(orixaDiaSemanaServices.save(orixaDiaSemanaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrixaDiaSemana(@PathVariable Long id) {
        orixaDiaSemanaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

