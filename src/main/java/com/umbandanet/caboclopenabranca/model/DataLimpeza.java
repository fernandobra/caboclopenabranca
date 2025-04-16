package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "datalimpeza")
@Data
public class DataLimpeza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "grupoLimpezaId")
    private Long grupoLimpezaId;
    @Column(name = "dataLimpeza")
    private Date dataLimpeza;
}
