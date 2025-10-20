package com.umbandanet.caboclopenabranca.controller;


import com.umbandanet.caboclopenabranca.model.Noticia;
import com.umbandanet.caboclopenabranca.repository.NoticiaRepository;
import com.umbandanet.caboclopenabranca.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/caboclopenabranca/noticias") // Prefixo para todas as rotas de notícias
public class NoticiaController {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private NoticiaService noticiaService;

    // Endpoint para LISTAR todas as notícias
    @GetMapping
    public ResponseEntity<List<Noticia>> listarNoticias() {
        List<Noticia> noticias = noticiaService.findAllByOrderByDataCriacaoDesc();
        return ResponseEntity.ok(noticias);
    }

    // Endpoint para CRIAR uma nova notícia com imagem
    // Usa 'multipart/form-data' para receber texto e arquivo juntos
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Noticia> criarNoticia(
            @RequestParam("titulo") String titulo,
            @RequestParam("conteudo") String conteudo,
            @RequestParam("imagem") MultipartFile imagem) {

        try {
            Noticia noticiaSalva = noticiaService.criarNoticia(titulo, conteudo, imagem);
            return new ResponseEntity<>(noticiaSalva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

