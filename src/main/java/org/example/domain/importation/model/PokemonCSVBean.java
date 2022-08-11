package org.example.domain.importation.model;

import liquibase.repackaged.com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class PokemonCSVBean {

    @CsvBindByName(column = "#")
    public int dexNumber;

    @CsvBindByName(column = "Name")
    public String name;

    @CsvBindByName(column = "Type 1")
    public String firstType;

    @CsvBindByName(column = "Type 2")
    public String secondType;

    @CsvBindByName(column = "HP")
    public String hp;

    @CsvBindByName(column = "Attack")
    public String atk;

    @CsvBindByName(column = "Defense")
    public String def;

    @CsvBindByName(column = "Sp. Atk")
    public String spAtk;

    @CsvBindByName(column = "Sp. Def")
    public String spDef;

    @CsvBindByName(column = "Speed")
    public String speed;

    @CsvBindByName(column = "Generation")
    public String generation;

    @CsvBindByName(column = "Legendary")
    public String legendary;
}
