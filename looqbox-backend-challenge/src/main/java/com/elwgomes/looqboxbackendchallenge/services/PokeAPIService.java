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

    public Pokedex searchPokemonBySubstring(String substring) {
        String url = POKEAPI_BASE_URL + POKEMON_ENDPOINT + "?limit=1000";

        RestTemplate restTemplate = new RestTemplate();

        // send a GET request to the PokeAPI and retrieve the response as a pokedex object
        ResponseEntity<Pokedex> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Pokedex.class);
        Pokedex response = responseEntity.getBody();

        if (response != null && response.getResults() != null) {
            // filter the list of pokemon based on the provided substring
            List<Pokemon> matchingPokemons = filterPokemonsBySubstring(response.getResults(), substring);

            // set the nameHighLight field for each matching pokemon
            matchingPokemons.forEach(pokemon -> pokemon.setNameHighLight(substring));

            // update the results field in the pokedex object with the matching pokemon list
            response.setResults(matchingPokemons);

            // return the updated pokedex object
            return response;
        }

        return null;
    }

    // filter the list of allPokemons based on the given substring
    private List<Pokemon> filterPokemonsBySubstring(List<Pokemon> allPokemons, String substring) {
        return allPokemons.stream()
                .filter(pokemon -> containsSubstring(pokemon.getName(), substring))
                .collect(Collectors.toList());
    }

    // check if the input string contains the given substring (case-insensitive)
    private boolean containsSubstring(String input, String substring) {
        return input.toLowerCase().contains(substring.toLowerCase());
    }
}
