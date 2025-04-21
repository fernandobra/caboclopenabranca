package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="materialpessoa")
@Data
public class MaterialPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pessoa_id;
    private Long material_id;
}