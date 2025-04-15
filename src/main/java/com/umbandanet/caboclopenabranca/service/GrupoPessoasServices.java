package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.dto.GrupoPessoaDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.model.GrupoPessoa;
import com.umbandanet.caboclopenabranca.model.Pessoas;

import java.util.List;
import java.util.Optional;

public interface GrupoPessoasServices {
    List<GrupoPessoa> findAll();
    Optional<GrupoPessoa> findById(Long id);
    GrupoPessoa save(GrupoPessoa grupoPessoa);
    void deleteById(Long id);
    List<GrupoPessoaDTO> findGrupoPessoas();
}

