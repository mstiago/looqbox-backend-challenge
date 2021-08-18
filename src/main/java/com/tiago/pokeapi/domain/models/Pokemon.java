package com.tiago.pokeapi.domain.models;

public class Pokemon{
    public String name;
    public String highlight;

    public Pokemon() {
    }
    
    public Pokemon(String name, String highlight) {
        this.name = name;
        this.highlight = highlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
