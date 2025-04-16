package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.DataLimpezaDTOProjection;
import com.umbandanet.caboclopenabranca.model.DataLimpeza;
import com.umbandanet.caboclopenabranca.model.Sessao;
import com.umbandanet.caboclopenabranca.service.DataLimpezaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/caboclopenabranca/datalimpeza")
public class DataLimpezaController {
    @Autowired
    private DataLimpezaServices dataLimpezaServices;

    @GetMapping
    public List<DataLimpeza> getAllDataLimpeza() {
        return dataLimpezaServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DataLimpeza> getDataLimpezaById(@PathVariable Long id) {
        return dataLimpezaServices.findById(id);
    }
    
    @PostMapping
    public DataLimpeza postDataLimpeza(@RequestBody DataLimpeza dataLimpeza) {
        return dataLimpezaServices.save(dataLimpeza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataLimpeza> updateDataLimpeza(@PathVariable Long id, @RequestBody DataLimpeza dataLimpezaDetails) {
        Optional<DataLimpeza> dataLimpeza = dataLimpezaServices.findById(id);
        if (dataLimpeza.isPresent()) {
            DataLimpeza dataLimpezaToUpdate = dataLimpeza.get();
            dataLimpezaToUpdate.setDataLimpeza(dataLimpezaDetails.getDataLimpeza());
            dataLimpezaToUpdate.setGrupo(dataLimpezaDetails.getGrupo());
            return ResponseEntity.ok(dataLimpezaServices.save(dataLimpezaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessao(@PathVariable Long id) {
        dataLimpezaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calendario")
    public  List<DataLimpezaDTOProjection> findDataLimpeza() {
        return dataLimpezaServices.findDataLimpeza();
    }

}

