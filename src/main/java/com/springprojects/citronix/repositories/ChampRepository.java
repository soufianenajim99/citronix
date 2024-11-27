package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChampRepository extends JpaRepository<Champ, Long> {  // Count the number of Champs for a specific Ferme
    long countByFermeId(Long idFerme);

    // Find all Champs for a specific Ferme
    List<Champ> findAllByFermeId(Long idFerme);

    // Check if any Champs exist for a specific Ferme
    boolean existsByFermeId(Long idFerme);

}
