package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public record DetailRecolteArbreDTO(
        Long id,
        @NotNull(message = "L'ID de l'arbre est requis") Long arbreId,
        double quantity
) {}

//@Data
//@Builder
//public class DetailRecoltesDTO {
//    private UUID id;
//    private UUID arbreId; // Reference to Arbre
//    private UUID recolteId; // Reference to Recolte
//    private double quantite;
//}

//@Builder
//@Data
//public class DetailRecoltesDTO {
//    private UUID id;
//
//    private ArbreDTO arbre;
//    private RecolteDTO recolte;
//
//    @DecimalMin(value = "0.1", message = "La quantité récoltée doit être supérieure à 0,1 kg")
//    private double quantite;
//}
