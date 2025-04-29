package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "data_limpeza")
@Data
public class DataLimpeza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "grupo_limpeza_id")
    private Long grupo_limpeza_id;
    @Column(name = "data_limpeza")
    private Date data_limpeza;
}
