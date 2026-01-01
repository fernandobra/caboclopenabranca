package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="grupo_pessoa")
@Data
public class GrupoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pessoa_id")
    private Long pessoaId;
    @Column(name = "grupo_limpeza_id")
    private Long grupoLimpezaId;
}