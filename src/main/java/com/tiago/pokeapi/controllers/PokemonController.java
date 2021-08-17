package com.tiago.pokeapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.tiago.pokeapi.models.Pokemon;
import com.tiago.pokeapi.services.PokemonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/pokemons")
@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> getPokemon(@RequestParam("q") String query, 
                                    @RequestParam(defaultValue = "length") String orderBy) {

        return pokemonService.GetPokemons(query, orderBy);
    }
}
