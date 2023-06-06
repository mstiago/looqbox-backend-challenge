package com.elwgomes.looqboxbackendchallenge.controllers;

import org.springframework.web.bind.annotation.*;

import com.elwgomes.looqboxbackendchallenge.entities.Pokedex;
import com.elwgomes.looqboxbackendchallenge.services.PokeAPIService;

@RestController
@RequestMapping("v1/api/pokemon")
public class PokemonController {

    private final PokeAPIService pokeAPIService;

    public PokemonController(PokeAPIService pokeAPIService) {
        this.pokeAPIService = pokeAPIService;
    }

    @GetMapping("/{substring}")
    public Pokedex searchPokemonBySubstring(@PathVariable("substring") String substring) {
        return pokeAPIService.searchPokemonBySubstring(substring);
    }
}

