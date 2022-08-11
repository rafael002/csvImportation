package org.example.domain.importation.exceptions;

public class FirstPokemonTypeIsRequiredException extends Exception{
    public FirstPokemonTypeIsRequiredException() {
        super("First pokémon type is required");
    }
}
