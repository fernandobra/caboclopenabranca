package com.umbandanet.caboclopenabranca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoPessoaDTO {
    private Long id;
    private String nome;
    private String grupo;
}