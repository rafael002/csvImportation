package org.example.infrastructure.repositories;

import org.example.domain.importation.entity.BaseStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseStatsRepository extends JpaRepository<BaseStats, Long> {
}
