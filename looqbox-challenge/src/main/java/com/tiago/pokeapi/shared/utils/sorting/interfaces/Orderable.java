package com.tiago.pokeapi.shared.utils.sorting.interfaces;

public interface Orderable<T> {
    boolean compare(T p1, T p2);
}