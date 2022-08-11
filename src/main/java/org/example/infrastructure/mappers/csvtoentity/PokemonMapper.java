package org.example.infrastructure.mappers.csvtoentity;

import org.example.domain.importation.entity.BaseStats;
import org.example.domain.importation.entity.Pokemon;
import org.example.domain.importation.entity.Type;
import org.example.domain.importation.model.PokemonCSVBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PokemonMapper {

    PokemonMapper INSTANCE = Mappers.getMapper( PokemonMapper.class );

    @Mapping(source = "bean.dexNumber", target = "dexNumber")
    @Mapping(source = "bean.name", target = "name")
    @Mapping(source = "bean.legendary", target = "legendary")
    @Mapping(source = "bean.generation", target = "generation")
    @Mapping(source = "typeOne", target = "typeOne")
    @Mapping(source = "typeTwo", target = "typeTwo")
    @Mapping(source = "baseStats", target = "baseStats")
    @Mapping(target = "id", ignore = true)
    Pokemon csvBeanToEntity(PokemonCSVBean bean, Type typeOne, Type typeTwo, BaseStats baseStats);


    @Mapping(source = "bean.dexNumber", target = "dexNumber")
    @Mapping(source = "bean.name", target = "name")
    @Mapping(source = "bean.legendary", target = "legendary")
    @Mapping(source = "bean.generation", target = "generation")
    @Mapping(source = "typeOne", target = "typeOne")
    @Mapping(source = "typeTwo", target = "typeTwo")
    @Mapping(source = "stats", target = "baseStats")
    @Mapping(target = "id", ignore = true)
    Pokemon updateEntity(@MappingTarget Pokemon pokemon, PokemonCSVBean bean, Type typeOne, Type typeTwo, BaseStats stats);
}
