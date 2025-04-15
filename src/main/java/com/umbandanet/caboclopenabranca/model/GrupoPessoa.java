package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GrupoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoas pessoa;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private GrupoLimpeza grupo;
}