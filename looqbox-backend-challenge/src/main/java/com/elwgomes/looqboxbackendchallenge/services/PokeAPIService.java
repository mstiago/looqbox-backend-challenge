package com.elwgomes.looqboxbackendchallenge.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.elwgomes.looqboxbackendchallenge.entities.Pokedex;
import com.elwgomes.looqboxbackendchallenge.entities.Pokemon;

@Service
public class PokeAPIService {

    private static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_ENDPOINT = "pokemon/";

    private final RestTemplate restTemplate;

    public PokeAPIService() {
        this.restTemplate = new RestTemplate();
    }

    public Pokedex searchPokemonBySubstring (String substring) {
        String url = POKEAPI_BASE_URL + POKEMON_ENDPOINT + "?limit=1000"; // Aumente o limite se necessário

        ResponseEntity<Pokedex> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Pokedex.class);
        Pokedex response = responseEntity.getBody();

        if (response != null && response.getResults() != null) {
            List<Pokemon> allPokemons = response.getResults();
            List<String> matchingPokemons = filterPokemonsBySubstring(allPokemons, substring);
            Pokedex pokedex = new Pokedex();
            pokedex.setResults(matchingPokemons);
            return pokedex;
        }

        return null; // Tratamento de erro adequado seria melhor
    }

    private List<String> filterPokemonsBySubstring(List<Pokemon> allPokemons, String substring) {
        return allPokemons.stream()
                .filter(pokemon -> pokemon.getName().contains(substring))
                .map(Pokemon::getName)
                .collect(Collectors.toList());
    }
}
