package org.example.infrastructure.repositories;

import org.example.domain.importation.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByDexNumber(int dexNumber);
}
