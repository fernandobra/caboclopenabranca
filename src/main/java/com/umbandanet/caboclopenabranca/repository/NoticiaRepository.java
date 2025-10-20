package com.umbandanet.caboclopenabranca.repository;


import com.umbandanet.caboclopenabranca.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    // Busca todas as notícias ordenando pela data de criação em ordem decrescente
    List<Noticia> findAllByOrderByDataCriacaoDesc();
}
