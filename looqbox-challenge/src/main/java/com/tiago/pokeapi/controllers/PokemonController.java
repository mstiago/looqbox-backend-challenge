package com.tiago.pokeapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.tiago.pokeapi.domain.models.Pokemon;
import com.tiago.pokeapi.services.PokemonRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    @Autowired
    private PokemonRepositoryImpl pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemon(@RequestParam("q") String query,
                                                    @RequestParam(defaultValue = "length") String orderBy) {

        List<Pokemon> pokemons = pokemonService.getFilteredPokemons(query, orderBy);

        return ResponseEntity.ok().body(pokemons);
    }
}
