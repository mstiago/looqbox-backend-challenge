package com.tiago.pokeapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.tiago.pokeapi.domain.models.GetPokemon;
import com.tiago.pokeapi.domain.models.Pokemon;
import com.tiago.pokeapi.domain.repository.PokemonRepository;
import com.tiago.pokeapi.shared.utils.sorting.AlphabeticalOrder;
import com.tiago.pokeapi.shared.utils.sorting.LengthOrder;
import com.tiago.pokeapi.shared.utils.sorting.Sort;
import com.tiago.pokeapi.shared.utils.sorting.interfaces.Orderable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonRepositoryImpl implements PokemonRepository{
    public static final String POKEMON_API_URL = "https://pokeapi.co/api/v2/pokemon?limit=2000";

    @Override
    public ResponseEntity<GetPokemon> getAllPokemons() {
        ResponseEntity<GetPokemon> response;

        try {
            response =  new RestTemplate().getForEntity(POKEMON_API_URL, GetPokemon.class);            
        }catch(RestClientException e){
            throw new RestClientException(e.getMessage());
        }

        return response;
    }

    private boolean filterPokemons(Pokemon pokemon,String query){
        if(pokemon.getName().startsWith(query)){
            String highlight  = String.format("<pre>%s</pre>%s", query, pokemon.getName().substring(query.length()));

            pokemon.setHighlight(highlight);

            return true;
        }else{
            return false;
        }
    }

    public List<Pokemon> getFilteredPokemons(String query, String orderBy) {
        Orderable<Pokemon> order = orderBy.equals("alphabetical") ? new AlphabeticalOrder() : new LengthOrder();

        List<Pokemon> pokemonsList =
                getAllPokemons()
                .getBody()
                .getResults()
                .stream()
                .filter(pokemon -> filterPokemons(pokemon, query))
                .collect(Collectors.toList());

        Sort.orderPokemons(pokemonsList, order);
                
        return pokemonsList;
    }
}
