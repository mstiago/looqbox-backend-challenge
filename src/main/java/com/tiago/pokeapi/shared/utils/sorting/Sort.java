package com.tiago.pokeapi.shared.utils.sorting;

import java.util.Collections;
import java.util.List;

import com.tiago.pokeapi.domain.models.Pokemon;
import com.tiago.pokeapi.shared.utils.sorting.interfaces.Orderable;

public class Sort {

	private static Orderable<Pokemon> sorting;

	public static void orderPokemons(List<Pokemon> pokemons, Orderable<Pokemon> order){
		sorting = order;

        sort(pokemons, 0, pokemons.size() - 1);
	}

	public static void sort(List<Pokemon> pokemons, int begin, int end)
	{
		if (begin < end)
		{
			int partitionIndex = partition(pokemons, begin, end);

			sort(pokemons, begin, partitionIndex - 1);
			sort(pokemons, partitionIndex + 1, end);
		}
	}

    private static int partition(List<Pokemon> pokemons, int begin, int end)
    {
        Pokemon pivot = pokemons.get(end);

        int beginIndex = begin;

        for (int currentIndex = beginIndex; currentIndex < end; currentIndex++)
        {
            if (sorting.compare(pivot , pokemons.get(currentIndex)))
            {
				Collections.swap(pokemons, beginIndex, currentIndex);
				//swapPokemons(pokemons, beginIndex, currentIndex);

                beginIndex++;
            }
        }

		Collections.swap(pokemons, beginIndex, end);
		//swapPokemons(pokemons, beginIndex, end);

        return beginIndex;
    }

	public static void swapPokemons(List<Pokemon> list, final int pos1, final int pos2){
		final Pokemon temp = list.get(pos1);
		list.set(pos1, list.get(pos2));
		list.set(pos2, temp);
	}
}