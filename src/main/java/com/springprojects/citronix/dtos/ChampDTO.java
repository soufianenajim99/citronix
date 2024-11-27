package com.springprojects.citronix.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.springprojects.citronix.models.Arbre;
import com.springprojects.citronix.models.Ferme;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;



public record ChampDTO(
        @NotBlank(message = "Le nom est obligatoire") String nom,
        @DecimalMin(value = "0.1", message = "La surface du champ doit être au moins de 0,1 hectare") double superficie,
        Long fermeId) {
}


