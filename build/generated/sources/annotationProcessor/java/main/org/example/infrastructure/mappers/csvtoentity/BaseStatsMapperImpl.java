package org.example.infrastructure.mappers.csvtoentity;

import javax.annotation.processing.Generated;
import org.example.domain.importation.entity.BaseStats;
import org.example.domain.importation.model.PokemonCSVBean;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T15:02:52-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class BaseStatsMapperImpl implements BaseStatsMapper {

    @Override
    public BaseStats csvBeanToEntity(PokemonCSVBean bean) {
        if ( bean == null ) {
            return null;
        }

        BaseStats baseStats = new BaseStats();

        if ( bean.getHp() != null ) {
            baseStats.setHp( Integer.parseInt( bean.getHp() ) );
        }
        if ( bean.getAtk() != null ) {
            baseStats.setAtk( Integer.parseInt( bean.getAtk() ) );
        }
        if ( bean.getDef() != null ) {
            baseStats.setDef( Integer.parseInt( bean.getDef() ) );
        }
        if ( bean.getSpAtk() != null ) {
            baseStats.setSpAtk( Integer.parseInt( bean.getSpAtk() ) );
        }
        if ( bean.getSpDef() != null ) {
            baseStats.setSpDef( Integer.parseInt( bean.getSpDef() ) );
        }
        if ( bean.getSpeed() != null ) {
            baseStats.setSpeed( Integer.parseInt( bean.getSpeed() ) );
        }

        return baseStats;
    }

    @Override
    public BaseStats update(BaseStats baseStats, PokemonCSVBean bean) {
        if ( bean == null ) {
            return baseStats;
        }

        if ( bean.getHp() != null ) {
            baseStats.setHp( Integer.parseInt( bean.getHp() ) );
        }
        if ( bean.getAtk() != null ) {
            baseStats.setAtk( Integer.parseInt( bean.getAtk() ) );
        }
        if ( bean.getDef() != null ) {
            baseStats.setDef( Integer.parseInt( bean.getDef() ) );
        }
        if ( bean.getSpAtk() != null ) {
            baseStats.setSpAtk( Integer.parseInt( bean.getSpAtk() ) );
        }
        if ( bean.getSpDef() != null ) {
            baseStats.setSpDef( Integer.parseInt( bean.getSpDef() ) );
        }
        if ( bean.getSpeed() != null ) {
            baseStats.setSpeed( Integer.parseInt( bean.getSpeed() ) );
        }

        return baseStats;
    }
}
