package com.tiago.pokeapi.shared.utils.sorting;

import com.tiago.pokeapi.models.Pokemon;
import com.tiago.pokeapi.shared.utils.sorting.interfaces.Orderable;

public class LengthOrder implements Orderable<Pokemon>{

    @Override
    public boolean compare(Pokemon p1, Pokemon p2) {
        return p1.getName().length() > p2.getName().length();
    }
}
