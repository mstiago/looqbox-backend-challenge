package com.elwgomes.looqboxbackendchallenge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elwgomes.looqboxbackendchallenge.entities.Pokedex;
import com.elwgomes.looqboxbackendchallenge.services.PokeAPIService;

@RestController
@RequestMapping("v1/api/pokemon")
public class PokemonController {

    private final PokeAPIService pokeAPIService;

    public PokemonController(PokeAPIService pokeAPIService) {
        this.pokeAPIService = pokeAPIService;
    }

    @GetMapping
    public Pokedex searchPokemonBySubstring(@RequestParam("substring") String substring) {
        return pokeAPIService.searchPokemonBySubstring(substring);
    }
}

