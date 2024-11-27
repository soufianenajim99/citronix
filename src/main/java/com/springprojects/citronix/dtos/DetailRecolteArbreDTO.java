package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


public record DetailRecolteArbreDTO(
        Long id,
        @NotNull(message = "L'ID de l'arbre est requis") Long arbreId,
        double quantity
) {}


