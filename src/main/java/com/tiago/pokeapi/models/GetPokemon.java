package com.tiago.pokeapi.models;

import java.util.List;

public class GetPokemon {
    
    private List<Pokemon> results;
    
    public List<Pokemon> getResults() {
        return results;
    }
    public void setResults(List<Pokemon> results) {
        this.results = results;
    }  
}
