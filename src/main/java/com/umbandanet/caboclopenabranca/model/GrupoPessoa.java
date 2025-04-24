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
    private Long pessoa_id;
    @Column(name = "grupo_limpeza_id")
    private Long grupo_limpeza_id;
}