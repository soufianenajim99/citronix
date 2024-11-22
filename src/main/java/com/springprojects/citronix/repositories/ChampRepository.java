package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChampRepository extends JpaRepository<Champ, UUID> {
    List<Champ> findByFermeId(UUID fermeId);
    long countByFermeId(UUID fermeId);
}
