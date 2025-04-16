package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="grupopessoa")
@Data
public class GrupoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pessoa_id;
    @Column(name = "grupolimpeza_id")
    private Long grupoLimpeza_id;
}