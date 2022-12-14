package com.twic.service;

import com.twic.model.VilleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@Service
public class ApiService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;

    @Autowired
    public ApiService() {
        this.localApiClient = WebClient.create("http://localhost:8181");
    }

    public List<VilleModel> getVilles() {
        return localApiClient
                .get()
                .uri("/ville")
                .retrieve()
                .toEntityList(VilleModel.class)
                .block(REQUEST_TIMEOUT)
                .getBody();
    }

    public VilleModel getVilleByCodeCommune(String codeCommune) {
        return localApiClient
                .get()
                .uri("/villeByCodeCommune?codeCommune=" + codeCommune)
                .retrieve()
                .bodyToMono(VilleModel.class)
                .block(REQUEST_TIMEOUT);
    }
}
