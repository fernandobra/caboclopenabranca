package com.umbandanet.caboclopenabranca.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTOProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.repository.PessoasRepository;
import com.umbandanet.caboclopenabranca.service.PessoasServices;

@Service
public class PessoasServicesImpl implements PessoasServices {

    @Autowired private PessoasRepository pessoasRepository;

    @Override
    public List<Pessoas> findAll() {
        return pessoasRepository.findAll();
    }

    @Override
    public Optional<Pessoas> findById(Long id) {
        return pessoasRepository.findById(id);
    }

    @Override
    public Pessoas save(Pessoas pessoas) {
        return pessoasRepository.save(pessoas);
    }

    @Override
    public void deleteById(Long id) {
         pessoasRepository.deleteById(id);
    }

    @Override
    public List<PessoaAniversarioDTO> buscarProximosAniversarios() {
        List<PessoaAniversarioDTOProjection> projections = pessoasRepository.findProximosAniversarios();
        return projections.stream()
                .map(projection -> new PessoaAniversarioDTO(projection.getId(), projection.getNome(), projection.getMes(), projection.getDia()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByLoginAndSenha(String login, String senha) {
        return pessoasRepository.existsByLoginAndSenha(login, senha);
    }

    @Override
    public Optional<Pessoas> validateByLoginAndSenha(String login, String senha) {
        return pessoasRepository.validateByLoginAndSenha(login, senha);
    }
}
