package org.example.domain.importation.services;

import liquibase.repackaged.com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.importation.entity.BaseStats;
import org.example.domain.importation.entity.Pokemon;
import org.example.domain.importation.entity.Type;
import org.example.domain.importation.exceptions.FirstPokemonTypeIsRequiredException;
import org.example.domain.importation.model.PokemonCSVBean;
import org.example.infrastructure.filters.CSVFilenameFilter;
import org.example.infrastructure.mappers.csvtoentity.BaseStatsMapper;
import org.example.infrastructure.mappers.csvtoentity.PokemonMapper;
import org.example.infrastructure.mappers.csvtoentity.TypeMapper;
import org.example.infrastructure.repositories.BaseStatsRepository;
import org.example.infrastructure.repositories.PokemonRepository;
import org.example.infrastructure.repositories.TypeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystemException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportationServiceImpl implements ImportationService {
    private final CSVFilenameFilter cSVFilenameFilter;
    private final TypeRepository typeRepository;
    private final PokemonRepository pokemonRepository;
    private Map<String, Type> typesAlreadySavedList;
    private List<String> availableTypeNames;

    @Value("${importation.path}")
    private String path;

    public void runImportation() {
        try {
            log.info("searching files in: " + path);
            File folder = new File(path);

            String[] filePaths = folder.list(cSVFilenameFilter);

            if (filePaths == null || filePaths.length < 1) {
                throw new FileNotFoundException();
            }

            int count = 0;
            for (String pathFile : filePaths) {
                try {
                    int affectedLinesNumber = this.importLines(importSinglePokemonFile(pathFile));
                    log.info("file: " + pathFile + " have been imported properly and " + affectedLinesNumber +
                            " registries were affected");
                    this.deleteFile(pathFile);
                    count++;
                } catch (FileNotFoundException fileNotFoundException) {
                    log.error("File not found! - file: " + pathFile);
                } catch (Exception exception) {
                    log.error("An exception occurs in importation process of file: " + pathFile, exception);
                }
            }
            log.info(count + " files have been successfully imported to datasource");

        } catch (FileNotFoundException e) {
            log.info("there`s no file to start the importation process.");
        }
    }

    private void getAllTypesList() {
        if (this.typesAlreadySavedList == null) {
            log.info("Getting files from datasource.");
            this.typesAlreadySavedList = typeRepository.findAll().stream()
                    .collect(Collectors.toMap(Type::getName, Type::returItself));

            this.availableTypeNames = new ArrayList<String>(typesAlreadySavedList.keySet());
            log.info("Types list has been set.");
        }
    }

    private List<PokemonCSVBean> importSinglePokemonFile(String filename) throws FileNotFoundException {
        String currentPath = path + filename;
        log.info("current file path: " + currentPath);
        List<PokemonCSVBean> lines = new CsvToBeanBuilder(new FileReader(currentPath))
                .withType(PokemonCSVBean.class).build().parse();
        log.info("file:" + currentPath + " has been parsed");
        return lines;
    }

    private void deleteFile(String filePath) throws Exception {
        File file = new File(path + filePath);

        if (!file.delete()) {
            log.error("Error - File cannot be deleted!: " + filePath);
            throw new Exception("Error - File cannot be deleted!: " + filePath);
        }
    }

    private Type findOrCreateType(String type) {
        Type mappedType = null;

        if (type == null) {
            return null;
        }

        // recover list of types in database (list is small)
        this.getAllTypesList();

        Type savedType;
        if (!this.availableTypeNames.contains(type)) {
            log.info("creating new type: " + type);
            mappedType = TypeMapper.INSTANCE.csvBeanToEntity(type);
            this.availableTypeNames.add(mappedType.getName());

            savedType = this.typeRepository.save(mappedType);
            this.typesAlreadySavedList.put(savedType.getName(), savedType);
            this.availableTypeNames.add(savedType.getName());
        } else {
            savedType = this.typesAlreadySavedList.get(type);
        }

        return savedType;
    }
    @Transactional
    private int importLines(List<PokemonCSVBean> lines) {
        try {
            int count = 0;
            for (PokemonCSVBean pokemon : lines) {
                // mapping types
                if (Objects.equals(pokemon.getFirstType(), "")) {
                    throw new FirstPokemonTypeIsRequiredException();
                }

                Type firstType = this.findOrCreateType(pokemon.getFirstType());

                // second type is optional
                Type secondType = null;
                if (!(Objects.equals(pokemon.getSecondType(), ""))) {
                    secondType = this.findOrCreateType(pokemon.getSecondType());
                }

                // update an existent monster
                List<Pokemon> pokemonEntityList = this.pokemonRepository.findByDexNumber(pokemon.getDexNumber());

                if (pokemonEntityList.isEmpty()) {
                    // mapping pokémon base stats
                    BaseStats pokemonBaseStats = BaseStatsMapper.INSTANCE.csvBeanToEntity(pokemon);

                    // mapping pokémon
                    Pokemon pocketMonsterEntity = PokemonMapper.INSTANCE.csvBeanToEntity(
                            pokemon,
                            firstType,
                            secondType,
                            pokemonBaseStats
                    );

                    pokemonRepository.save(pocketMonsterEntity);

                    log.info("A new Pokemon has been imported! " + pokemon.getName());
                    count++;
                } else {
                    Pokemon pocketMonsterUpdateEntity = pokemonEntityList.get(0);

                    // mapping pokémon base stats
                    BaseStats pokemonBaseStats = BaseStatsMapper.INSTANCE.update(
                            pocketMonsterUpdateEntity.getBaseStats(), pokemon);

                    // mapping pokémon
                    Pokemon pocketMonsterEntity = PokemonMapper.INSTANCE.updateEntity(
                            pocketMonsterUpdateEntity,
                            pokemon,
                            firstType,
                            secondType,
                            pokemonBaseStats
                    );

                    pokemonRepository.save(pocketMonsterEntity);

                    count++;
                    log.info("Pokedex entry number: " + pocketMonsterEntity.getDexNumber() + " data has been updated! ");
                }
            }
            return count;
        } catch (FirstPokemonTypeIsRequiredException firstPokemonTypeIsRequiredException) {
            throw new RuntimeException(firstPokemonTypeIsRequiredException);
        }
    }
}

