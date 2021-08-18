package com.tiago.pokeapi.domain.repository;

import com.tiago.pokeapi.domain.models.GetPokemon;

import org.springframework.http.ResponseEntity;

public interface PokemonRepository {

    public ResponseEntity<GetPokemon> getAllPokemons();
    
}
