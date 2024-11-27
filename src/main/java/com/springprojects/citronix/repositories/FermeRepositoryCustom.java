package com.springprojects.citronix.repositories;

import com.springprojects.citronix.dtos.CritereRechercheFerme;
import com.springprojects.citronix.models.Ferme;

import java.util.List;

public interface FermeRepositoryCustom {
    List<Ferme> rechercherFerme(CritereRechercheFerme fermeSearchCriteria);
}
