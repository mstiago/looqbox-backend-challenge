package com.elwgomes.looqboxbackendchallenge.services;

import com.elwgomes.looqboxbackendchallenge.entities.Pokedex;
import com.elwgomes.looqboxbackendchallenge.entities.PokedexTest;

import com.elwgomes.looqboxbackendchallenge.entities.Pokemon;
import com.elwgomes.looqboxbackendchallenge.entities.PokemonTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PokeAPIServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokeAPIService pokeAPIService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchPokemonBySubstring() {
        // Mock the response from the PokeAPI
        PokedexTest pokedexTest = new PokedexTest();
        PokemonTest pokemon1 = new PokemonTest("charizard");
        PokemonTest pokemon2 = new PokemonTest("charmander");
        pokedexTest.setResults(Arrays.asList(pokemon1, pokemon2));
        ResponseEntity<PokedexTest> responseEntity = ResponseEntity.ok(pokedexTest);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(PokedexTest.class)))
                .thenReturn(responseEntity);

        // perform the search
        Pokedex response = pokeAPIService.searchPokemonBySubstring("charm");

        // verify the results
        assertNotNull(response);
        List<Pokemon> results = response.getResults();
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("charmander", results.get(0).getName());
        assertEquals("charmeleon", results.get(1).getName());
    }

    @Test
    public void testSearchPokemonBySubstringAlphabetic() {
        PokedexTest pokedexTest = new PokedexTest();
        PokemonTest pokemon1 = new PokemonTest("venusaur");
        PokemonTest pokemon2 = new PokemonTest("ivysaur");
        PokemonTest pokemon3 = new PokemonTest("bulbasaur");
        pokedexTest.setResults(Arrays.asList(pokemon1, pokemon2, pokemon3));
        ResponseEntity<PokedexTest> responseEntity = ResponseEntity.ok(pokedexTest);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(PokedexTest.class)))
                .thenReturn(responseEntity);

        Pokedex response = pokeAPIService.searchPokemonBySubstringAlphabetic("saur");

        assertNotNull(response);
        List<Pokemon> results = response.getResults();
        assertNotNull(results);
        assertEquals(3, results.size());
        assertEquals("bulbasaur", results.get(0).getName());
        assertEquals("ivysaur", results.get(1).getName());
        assertEquals("venusaur", results.get(2).getName());

    }

    @Test
    public void testSearchPokemonBySubstringLength() {
        PokedexTest pokedexTest = new PokedexTest();
        PokemonTest pokemon1 = new PokemonTest("venusaur");
        PokemonTest pokemon2 = new PokemonTest("ivysaur");
        PokemonTest pokemon3 = new PokemonTest("bulbasaur");
        pokedexTest.setResults(Arrays.asList(pokemon1, pokemon2, pokemon3));
        ResponseEntity<PokedexTest> responseEntity = ResponseEntity.ok(pokedexTest);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(PokedexTest.class)))
                .thenReturn(responseEntity);

        Pokedex response = pokeAPIService.searchPokemonBySubstringLength("saur");

        assertNotNull(response);
        List<Pokemon> results = response.getResults();
        assertNotNull(results);
        assertEquals(3, results.size());
        assertEquals("ivysaur", results.get(0).getName());
        assertEquals("venusaur", results.get(1).getName());
        assertEquals("bulbasaur", results.get(2).getName());
    }
}
