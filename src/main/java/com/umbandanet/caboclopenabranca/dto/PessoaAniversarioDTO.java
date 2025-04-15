package com.umbandanet.caboclopenabranca.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaAniversarioDTO {
    private Long id;
    private String nome;
    private String mes;
    private int dia;
}