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
    @NotNull(message = "Un arbre doit être associé au détail de la récolte")
    private String arbreId;
    @NotNull(message = "Une récolte doit être associée au détail")
    private String recolteId;

    @DecimalMin(value = "0.1", message = "La quantité récoltée doit être supérieure à 0,1 kg")
    private double quantite;
}
