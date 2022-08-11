package org.example.infrastructure.repositories;

import org.example.domain.importation.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findByName(String name);
}
