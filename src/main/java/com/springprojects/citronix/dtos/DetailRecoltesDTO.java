package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class DetailRecoltesDTO {
    private UUID id;

    private ArbreDTO arbre;
    private RecolteDTO recolte;

    @DecimalMin(value = "0.1", message = "La quantité récoltée doit être supérieure à 0,1 kg")
    private double quantite;
}
