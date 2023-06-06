package com.elwgomes.looqboxbackendchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.elwgomes.looqboxbackendchallenge.entities.Pokedex;
import com.elwgomes.looqboxbackendchallenge.services.PokeAPIService;

@RestController
@RequestMapping("v1/api/pokemon")
public class PokemonController {

    @Autowired
    private PokeAPIService pokeAPIService;

    public PokemonController(PokeAPIService pokeAPIService) {
        this.pokeAPIService = pokeAPIService;
    }

    @GetMapping("/{substring}")
    public Pokedex searchPokemonBySubstring(@PathVariable("substring") String substring) {
        return pokeAPIService.searchPokemonBySubstring(substring);
    }

    @GetMapping("/alph/{substring}")
    public Pokedex searchPokemonBySubstringAlphabetic(@PathVariable("substring") String substring) {
        return pokeAPIService.searchPokemonBySubstringAlphabetic(substring);
    }

    @GetMapping("/length/{substring}")
    public Pokedex searchPokemonBySubstringLenght(@PathVariable("substring") String substring) {
        return pokeAPIService.searchPokemonBySubstringLength(substring);
    }

}

