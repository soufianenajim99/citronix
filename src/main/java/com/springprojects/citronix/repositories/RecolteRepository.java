package com.springprojects.citronix.repositories;

import com.springprojects.citronix.models.Recolte;
import com.springprojects.citronix.utils.enums.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    boolean existsBySaisonAndAndChampId(Saison saison, Long idChamp);

}
