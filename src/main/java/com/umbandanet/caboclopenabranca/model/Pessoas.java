package com.umbandanet.caboclopenabranca.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome ;
    private String cpf ;
    private Date dataNascimento;
    private String endereco;
    private String numero ; 
    private String complemento; 
    private String bairro; 
    private String cidade; 
    private String estado; 
    private String status ; 
    private String email ; 
    private String login ;
    private String senha;
    private String celular;
    private String foto_usuario;
}
