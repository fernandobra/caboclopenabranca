package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.model.Noticia;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface  NoticiaService {
    List<Noticia> findAllByOrderByDataCriacaoDesc();
    Noticia criarNoticia(String titulo, String conteudo, MultipartFile imagem);
}
