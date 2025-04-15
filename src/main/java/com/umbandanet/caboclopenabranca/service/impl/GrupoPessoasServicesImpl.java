package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.dto.GrupoPessoaDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTOProjection;
import com.umbandanet.caboclopenabranca.model.GrupoPessoa;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.repository.GrupoPessoasRepository;
import com.umbandanet.caboclopenabranca.repository.PessoasRepository;
import com.umbandanet.caboclopenabranca.service.GrupoPessoasServices;
import com.umbandanet.caboclopenabranca.service.PessoasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrupoPessoasServicesImpl implements GrupoPessoasServices {

    @Autowired private GrupoPessoasRepository grupoPessoasRepository;

    @Override
    public List<GrupoPessoa> findAll() {
        return grupoPessoasRepository.findAll();
    }

    @Override
    public Optional<GrupoPessoa> findById(Long id) {
        return grupoPessoasRepository.findById(id);
    }

    @Override
    public GrupoPessoa save(GrupoPessoa grupoPessoa) {
        return grupoPessoasRepository.save(grupoPessoa);
    }

    @Override
    public void deleteById(Long id) {
        grupoPessoasRepository.deleteById(id);
    }

    @Override
    public List<GrupoPessoaDTO> findGrupoPessoas() {
        return grupoPessoasRepository.findGrupoPessoas();
    }
}
