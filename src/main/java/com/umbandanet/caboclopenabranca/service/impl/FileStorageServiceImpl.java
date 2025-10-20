package com.umbandanet.caboclopenabranca.service.impl;


import org.springframework.beans.factory.annotation.Value;
import com.umbandanet.caboclopenabranca.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl  implements FileStorageService {

    private final Path fileStorageLocation;
    // Injeta o valor da propriedade app.base-url do application.properties
    @Value("${app.base-url}")
    private String baseUrl;

    public FileStorageServiceImpl() {
        // Define o diretório onde as imagens serão salvas
        this.fileStorageLocation = Paths.get("./uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório para upload de arquivos.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Gera um nome de arquivo único para evitar conflitos
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // --- LÓGICA CORRIGIDA PARA CONSTRUIR A URL ---
            // Não use mais ServletUriComponentsBuilder, pois ele pode gerar 'localhost'.
            // Construa a URL manualmente com a base que definimos.
            // O caminho /api/caboclopenabranca/uploads/ é como você configurou o WebConfig
            // para servir os arquivos estáticos.
            return baseUrl + "/uploads/" + fileName;

        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível armazenar o arquivo " + fileName, ex);
        }
    }
}