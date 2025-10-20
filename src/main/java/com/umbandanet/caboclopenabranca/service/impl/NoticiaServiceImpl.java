package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.model.Noticia;
import com.umbandanet.caboclopenabranca.repository.NoticiaRepository;
import com.umbandanet.caboclopenabranca.service.FileStorageService;
import com.umbandanet.caboclopenabranca.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private FileStorageService fileStorageService;


    public List<Noticia> findAllByOrderByDataCriacaoDesc() {
        return noticiaRepository.findAllByOrderByDataCriacaoDesc();
    }

    public Noticia criarNoticia(String titulo, String conteudo, MultipartFile imagem) {
        String imageUrl = fileStorageService.storeFile(imagem);

        Noticia novaNoticia = new Noticia();
        novaNoticia.setTitulo(titulo);
        novaNoticia.setConteudo(conteudo);
        novaNoticia.setImageUrl(imageUrl);

        return noticiaRepository.save(novaNoticia);
    }
}
