package com.umbandanet.caboclopenabranca.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umbandanet.caboclopenabranca.model.Noticia;
import com.umbandanet.caboclopenabranca.repository.NoticiaRepository;
import com.umbandanet.caboclopenabranca.service.NoticiaService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    // Cliente HTTP para se comunicar com o script PHP
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // URL do seu script PHP
    private final String PHP_UPLOAD_URL = "https://umbandanet.com.br/upload_imagem.php";
    // Chave secreta que deve ser a mesma do script PHP
    private final String API_KEY = "X9t@L3vQ#pR8mZ!sW2u$K7bN&yT4d"; // USE A MESMA CHAVE!

    public List<Noticia> findAllByOrderByDataCriacaoDesc(){
        return noticiaRepository.findAllByOrderByDataCriacaoDesc();
    }

    public Noticia criarNoticia(String titulo, String conteudo, MultipartFile imagem) throws IOException {
        // Passo 1: Fazer o upload da imagem para o servidor web via script PHP
        String imageUrl = uploadImagemParaServidorWeb(imagem);

        if (imageUrl == null) {
            throw new IOException("Não foi possível obter a URL da imagem do servidor web.");
        }

        // Passo 2: Salvar a notícia no seu banco de dados com a URL retornada
        Noticia novaNoticia = new Noticia();
        novaNoticia.setTitulo(titulo);
        novaNoticia.setConteudo(conteudo);
        novaNoticia.setImageUrl(imageUrl); // Salva a URL pública (ex: https://...)

        return noticiaRepository.save(novaNoticia);
    }

    private String uploadImagemParaServidorWeb(MultipartFile imagem) throws IOException {
        MediaType mediaType = MediaType.parse(imagem.getContentType());
        RequestBody fileBody = RequestBody.create(mediaType, imagem.getBytes());

        // Constrói o corpo da requisição multipart para o PHP
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("api_key", API_KEY) // Envia a chave secreta
                .addFormDataPart("imagem", imagem.getOriginalFilename(), fileBody)
                .build();

        // Cria a requisição POST
        Request request = new Request.Builder()
                .url(PHP_UPLOAD_URL)
                .post(requestBody)
                .build();

        // Executa a requisição e obtém a resposta
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na chamada ao script PHP: " + response);
            }

            // Analisa a resposta JSON do PHP para extrair a URL
            String responseBody = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            if (jsonNode.has("status") && jsonNode.get("status").asText().equals("success") && jsonNode.has("url")) {
                return jsonNode.get("url").asText();
            } else {
                throw new IOException("Script PHP retornou um erro: " + responseBody);
            }
        }
    }
}
