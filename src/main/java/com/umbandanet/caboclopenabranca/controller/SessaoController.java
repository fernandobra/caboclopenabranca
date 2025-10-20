package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.model.Sessao;
import com.umbandanet.caboclopenabranca.service.SessaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/sessao")
public class SessaoController {
    @Autowired
    private SessaoServices sessaoServices;

    @GetMapping
    public List<Sessao> getAllSessaos() {
        return sessaoServices.findAllRegister();
    }

    @GetMapping("/{id}")
    public Optional<Sessao> getSessaoById(@PathVariable Long id) {
        return sessaoServices.findById(id);
    }
    
    @PostMapping
    public Sessao postSessao(@RequestBody Sessao sessao) {
        return sessaoServices.save(sessao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessao> putSessao(@PathVariable Long id, @RequestBody Sessao sessaoDetails) {
        Optional<Sessao> sessao = sessaoServices.findById(id);
        if (sessao.isPresent()) {
            Sessao sessaoToUpdate = sessao.get();
            sessaoToUpdate.setData(sessaoDetails.getData());
            sessaoToUpdate.setHora(sessaoDetails.getHora());
            sessaoToUpdate.setDia(sessaoDetails.getDia());
            sessaoToUpdate.setOrixa(sessaoDetails.getOrixa());
            sessaoToUpdate.setEntidade(sessaoDetails.getEntidade());

            return ResponseEntity.ok(sessaoServices.save(sessaoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessao(@PathVariable Long id) {
        sessaoServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

