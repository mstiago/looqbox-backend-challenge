package com.tiago.pokeapi.shared.utils.sorting;

import com.tiago.pokeapi.models.Pokemon;
import com.tiago.pokeapi.shared.utils.sorting.interfaces.Orderable;

public class AlphabeticalOrder implements Orderable<Pokemon>{

    @Override
    public boolean compare(Pokemon p1, Pokemon p2) {
        return p1.getName().compareTo(p2.getName()) > 0;
    }
}
