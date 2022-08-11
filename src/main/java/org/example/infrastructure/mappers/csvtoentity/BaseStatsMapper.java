package org.example.infrastructure.mappers.csvtoentity;

import org.example.domain.importation.entity.BaseStats;
import org.example.domain.importation.model.PokemonCSVBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BaseStatsMapper {
    BaseStatsMapper INSTANCE = Mappers.getMapper( BaseStatsMapper.class );

    @Mapping(source = "bean.hp", target = "hp")
    @Mapping(source = "bean.atk", target = "atk")
    @Mapping(source = "bean.def", target = "def")
    @Mapping(source = "bean.spAtk", target = "spAtk")
    @Mapping(source = "bean.spDef", target = "spDef")
    @Mapping(source = "bean.speed", target = "speed")
    BaseStats csvBeanToEntity(PokemonCSVBean bean);

    @Mapping(source = "bean.hp", target = "hp")
    @Mapping(source = "bean.atk", target = "atk")
    @Mapping(source = "bean.def", target = "def")
    @Mapping(source = "bean.spAtk", target = "spAtk")
    @Mapping(source = "bean.spDef", target = "spDef")
    @Mapping(source = "bean.speed", target = "speed")
    BaseStats update(@MappingTarget BaseStats baseStats, PokemonCSVBean bean);
}
