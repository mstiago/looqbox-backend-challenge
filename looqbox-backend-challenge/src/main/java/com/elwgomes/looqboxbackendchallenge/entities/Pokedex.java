package com.elwgomes.looqboxbackendchallenge.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokedex {

    private List<Pokemon> results;

    public void setResults(List<String> matchingPokemons) {
    }
}
