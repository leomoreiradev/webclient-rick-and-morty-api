package com.api.webclientrickandmortyapi.controller;


import com.api.webclientrickandmortyapi.client.RickAndMortyClient;
import com.api.webclientrickandmortyapi.response.CharacterResponse;
import com.api.webclientrickandmortyapi.response.EpisodeResponse;
import com.api.webclientrickandmortyapi.response.ListOfEpisodesResponse;
import com.api.webclientrickandmortyapi.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/webclient")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyClient rickAndMortyClient;

    @GetMapping(value = "/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findCharacterById(id);
    }

    @GetMapping(value = "/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findLocationById(id);
    }

    @GetMapping(value = "/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findEpisodeById(id);
    }

    @GetMapping(value = "/episodes")
    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        return rickAndMortyClient.getAllEpisodes();
    }
}
