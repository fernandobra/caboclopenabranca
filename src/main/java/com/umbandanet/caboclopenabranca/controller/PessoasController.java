package com.umbandanet.caboclopenabranca.controller;

import java.util.List;
import java.util.Optional;

import com.umbandanet.caboclopenabranca.dto.LoginRequest;
import com.umbandanet.caboclopenabranca.dto.PessoaAniversarioDTO;
import com.umbandanet.caboclopenabranca.security.JwtUtil;
import com.umbandanet.caboclopenabranca.util.CryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.service.PessoasServices;

@Slf4j
@RestController
@RequestMapping("/api/caboclopenabranca/pessoas")
public class PessoasController {
    @Autowired
    private PessoasServices pessoasServices;

    private String secretKey;

    @GetMapping
    public List<Pessoas> getAllPessoass() {
        return pessoasServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pessoas> getPessoasById(@PathVariable Long id) {
        Optional<Pessoas> pessoas = pessoasServices.findById(id);
        return pessoas;
    }
    
    @PostMapping
    public ResponseEntity<Pessoas> createPessoas(@RequestBody Pessoas pessoas) {
        return ResponseEntity.ok(pessoasServices.save(pessoas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoas> updatePessoas(@PathVariable Long id, @RequestBody Pessoas pessoasDetails) {
        Optional<Pessoas> pessoas = pessoasServices.findById(id);
        if (pessoas.isPresent()) {
            Pessoas pessoasToUpdate = pessoas.get();
            pessoasToUpdate.setBairro(pessoasDetails.getBairro());
            pessoasToUpdate.setCidade(pessoasDetails.getCidade());
            pessoasToUpdate.setComplemento(pessoasDetails.getComplemento());
            pessoasToUpdate.setCpf(pessoasDetails.getCpf());
            pessoasToUpdate.setDataNascimento(pessoasDetails.getDataNascimento());
            pessoasToUpdate.setEmail(pessoasDetails.getEmail());
            pessoasToUpdate.setEndereco(pessoasDetails.getEndereco());
            pessoasToUpdate.setEstado(pessoasDetails.getEstado());
            pessoasToUpdate.setLogin(pessoasDetails.getLogin());
            pessoasToUpdate.setNome(pessoasDetails.getNome());
            pessoasToUpdate.setNumero(pessoasDetails.getNumero());
            pessoasToUpdate.setSenha(pessoasDetails.getSenha());
            pessoasToUpdate.setCelular(pessoasDetails.getCelular());
            pessoasToUpdate.setFoto_usuario(pessoasDetails.getFoto_usuario());
            pessoasToUpdate.setStatus(pessoasDetails.getStatus());

            return ResponseEntity.ok(pessoasServices.save(pessoasToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        pessoasServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proximos-aniversarios")
    public List<PessoaAniversarioDTO> getProximosAniversarios() {
        return pessoasServices.buscarProximosAniversarios();
    }

    @GetMapping("/validar-usuario")
    public ResponseEntity<Boolean> validarUsuario(@RequestParam String login, @RequestParam String senha) {
        boolean isValid = pessoasServices.existsByLoginAndSenha(login, senha);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/validar-usuario-login-senha")
    public Optional<Pessoas> validarUsuarioLoginSenha(@RequestBody LoginRequest loginRequest) throws Exception {

        log.info("validarUsuarioLoginSenha -> LoginRequest -> getInformationFront: " + loginRequest.getInformationFront());
        log.info("validarUsuarioLoginSenha -> LoginRequest -> getUsername: " + loginRequest.getUsername());
        log.info("validarUsuarioLoginSenha -> LoginRequest -> getPassword: " + loginRequest.getPassword());

        return pessoasServices.validateByLoginAndSenha(
                loginRequest.getUsername(),
                loginRequest.getPassword()
//                CryptoUtil.decrypt(loginRequest.getUsername(),
//                        loginRequest.getInformationFront().trim()),
//                CryptoUtil.decrypt(loginRequest.getPassword(),
//                        loginRequest.getInformationFront().trim())
        );

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {

        log.info("login -> LoginRequest -> getInformationFront: " + loginRequest.getInformationFront());
        log.info("login -> LoginRequest -> getUsername: " + loginRequest.getUsername());
        log.info("login -> LoginRequest -> getPassword: " + loginRequest.getPassword());
        Optional<Pessoas> pessoas = pessoasServices.validateByLoginAndSenha(
                loginRequest.getUsername(),
                loginRequest.getPassword()
//                CryptoUtil.decrypt(loginRequest.getUsername(),
//                        loginRequest.getInformationFront().trim()),
//                CryptoUtil.decrypt(loginRequest.getPassword(),
//                        loginRequest.getInformationFront().trim())
        );

        if (pessoas.isPresent()) {
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @GetMapping("/login/exists")
    public ResponseEntity<Boolean> validarLogin(@RequestParam String login) {
        boolean isValid = pessoasServices.existsByLogin(login);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/email/exists")
    public ResponseEntity<Boolean> validarEmail(@RequestParam String email) {
        boolean isValid = pessoasServices.existsByEmail(email);
        return ResponseEntity.ok(isValid);
    }
}

