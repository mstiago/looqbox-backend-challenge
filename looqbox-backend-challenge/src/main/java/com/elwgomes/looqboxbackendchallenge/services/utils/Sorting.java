package com.elwgomes.looqboxbackendchallenge.services.utils;

import com.elwgomes.looqboxbackendchallenge.entities.Pokemon;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class Sorting {

    // using bubble sort to create two sorting algorithms


    public static void sortByLength(List<Pokemon> pokemons) {
        int n = pokemons.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                Pokemon pokemon1 = pokemons.get(j);
                Pokemon pokemon2 = pokemons.get(j + 1);

                if (pokemon1.getName().length() > pokemon2.getName().length()) {
                    pokemons.set(j, pokemon2);
                    pokemons.set(j + 1, pokemon1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }


    public static void sortByAlphabeticalOrder(List<Pokemon> pokemons) {
        int n = pokemons.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Pokemon pokemon1 = pokemons.get(j);
                Pokemon pokemon2 = pokemons.get(j + 1);
                if (comparePokemonNames(pokemon1.getName(), pokemon2.getName()) > 0) {
                    swap(pokemons, j, j + 1);
                }
            }
        }
    }

    private static int comparePokemonNames(String name1, String name2) {
        int n1 = name1.length();
        int n2 = name2.length();
        int minLen = Math.min(n1, n2);

        for (int i = 0; i < minLen; i++) {
            char c1 = name1.charAt(i);
            char c2 = name2.charAt(i);

            if (c1 != c2) {
                return c1 - c2;
            }
        }

        return n1 - n2;
    }

    private static void swap(List<Pokemon> pokemons, int i, int j) {
        Pokemon temp = pokemons.get(i);
        pokemons.set(i, pokemons.get(j));
        pokemons.set(j, temp);
    }

}
