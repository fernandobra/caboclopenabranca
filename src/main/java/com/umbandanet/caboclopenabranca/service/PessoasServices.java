package com.umbandanet.caboclopenabranca.service;

import java.util.List;
import java.util.Optional;

import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.model.Pessoas;

public interface PessoasServices {
    List<Pessoas> findAll();
    Optional<Pessoas> findById(Long id);
    Pessoas save(Pessoas pessoas);
    void deleteById(Long id);
    List<PessoaAniversarioDTO> buscarProximosAniversarios();
    boolean existsByLoginAndSenha(String login, String senha);
    Optional<Pessoas> validateByLoginAndSenha(String login, String senha);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}

