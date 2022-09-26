package com.api.webclientrickandmortyapi.client;

import com.api.webclientrickandmortyapi.response.CharacterResponse;
import com.api.webclientrickandmortyapi.response.EpisodeResponse;
import com.api.webclientrickandmortyapi.response.ListOfEpisodesResponse;
import com.api.webclientrickandmortyapi.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RickAndMortyClient {

    private final WebClient webClient;

    @Value("${base.url.client}")
    private String baseUrl;

    public Mono<CharacterResponse> findCharacterById(String id) {
        log.info("Buscando o personagem por id [{}]", id);

        return webClient.get()
                .uri( baseUrl + "/character/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findLocationById(String id) {
        log.info("Buscando localização por id [{}]", id);

        return webClient.get()
                .uri( baseUrl + "/location/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findEpisodeById(String id) {
        log.info("Buscando localização por id [{}]", id);

        return webClient.get()
                .uri( baseUrl + "/episode/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(EpisodeResponse.class);
    }


    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        log.info("Buscando lista de episodios");

        return webClient.get()
                .uri( baseUrl + "/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }
}
