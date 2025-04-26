package com.umbandanet.caboclopenabranca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface DataLimpezaDTOProjection {
    Long getId();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date getData_limpeza();
    String getNome();
    String getGrupo();
}