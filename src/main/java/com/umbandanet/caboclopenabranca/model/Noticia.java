package com.umbandanet.caboclopenabranca.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name="Noticia")
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Lob // Indica que pode ser um texto longo
    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp // Preenche automaticamente com a data de criação
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    // Construtor vazio (obrigatório para JPA)
    public Noticia() {}

}