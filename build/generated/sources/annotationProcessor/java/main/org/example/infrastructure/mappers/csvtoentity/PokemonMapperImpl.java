package org.example.infrastructure.mappers.csvtoentity;

import javax.annotation.processing.Generated;
import org.example.domain.importation.entity.BaseStats;
import org.example.domain.importation.entity.Pokemon;
import org.example.domain.importation.entity.Type;
import org.example.domain.importation.model.PokemonCSVBean;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T15:02:52-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class PokemonMapperImpl implements PokemonMapper {

    @Override
    public Pokemon csvBeanToEntity(PokemonCSVBean bean, Type typeOne, Type typeTwo, BaseStats baseStats) {
        if ( bean == null && typeOne == null && typeTwo == null && baseStats == null ) {
            return null;
        }

        Pokemon pokemon = new Pokemon();

        if ( bean != null ) {
            pokemon.setDexNumber( bean.getDexNumber() );
            pokemon.setName( bean.getName() );
            if ( bean.getLegendary() != null ) {
                pokemon.setLegendary( Boolean.parseBoolean( bean.getLegendary() ) );
            }
            if ( bean.getGeneration() != null ) {
                pokemon.setGeneration( Integer.parseInt( bean.getGeneration() ) );
            }
        }
        pokemon.setTypeOne( typeOne );
        pokemon.setTypeTwo( typeTwo );
        pokemon.setBaseStats( baseStats );

        return pokemon;
    }

    @Override
    public Pokemon updateEntity(Pokemon pokemon, PokemonCSVBean bean, Type typeOne, Type typeTwo, BaseStats stats) {
        if ( bean == null && typeOne == null && typeTwo == null && stats == null ) {
            return pokemon;
        }

        if ( bean != null ) {
            pokemon.setDexNumber( bean.getDexNumber() );
            pokemon.setName( bean.getName() );
            if ( bean.getLegendary() != null ) {
                pokemon.setLegendary( Boolean.parseBoolean( bean.getLegendary() ) );
            }
            if ( bean.getGeneration() != null ) {
                pokemon.setGeneration( Integer.parseInt( bean.getGeneration() ) );
            }
        }
        pokemon.setTypeOne( typeOne );
        pokemon.setTypeTwo( typeTwo );
        pokemon.setBaseStats( stats );

        return pokemon;
    }
}
