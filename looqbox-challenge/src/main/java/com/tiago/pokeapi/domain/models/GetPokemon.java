package com.tiago.pokeapi.domain.models;

import java.util.List;

import lombok.Data;

@Data
public class GetPokemon {
    
    private List<Pokemon> results;

}