package com.umbandanet.caboclopenabranca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialPessoaLoteRequestDTO {
    private Long materialId;
    private List<Long> pessoaIds;
}
