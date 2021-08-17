package com.tiago.pokeapi.services;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import com.tiago.pokeapi.models.GetPokemon;
import com.tiago.pokeapi.models.Pokemon;
import com.tiago.pokeapi.shared.utils.sorting.AlphabeticalOrder;
import com.tiago.pokeapi.shared.utils.sorting.LengthOrder;
import com.tiago.pokeapi.shared.utils.sorting.Sort;
import com.tiago.pokeapi.shared.utils.sorting.interfaces.Orderable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    public static final String POKEMON_API_URL = "https://pokeapi.co/api/v2/pokemon?limit=2000";

    public List<Pokemon> GetPokemons(String query, String orderBy){
        Orderable<Pokemon> order = orderBy.equals("alphabetical")  ? new AlphabeticalOrder() : new LengthOrder();

        List<Pokemon> pokemonsList =
                listPokemons()
                .stream()
                .filter(pokemon -> filterPokemons(pokemon, query))
                .collect(Collectors.toList());

        Sort.orderPokemons(pokemonsList, order);
                
        return pokemonsList;
    }
    
    public List<Pokemon> listPokemons() {

        ResponseEntity<GetPokemon> response;

        try {
            response =  new RestTemplate().getForEntity(POKEMON_API_URL, GetPokemon.class);            
        }catch(RestClientException e){
            throw new RestClientException(e.getMessage());
        }

        List<Pokemon> pokemonList = new ArrayList<Pokemon>();

        pokemonList.addAll(response.getBody().getResults());

        return pokemonList; 
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
}
