package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.Arbre;
import com.springprojects.citronix.models.Ferme;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ChampDTO {
    private UUID id;
    @NotBlank(message = "Le nom du champ ne peut pas être vide")
    private String nom;
    @DecimalMin(value = "0.1", message = "La superficie d'un champ doit être au moins de 0,1 hectare (1 000 m²)")
//    @DecimalMax(value = "50", message = "La superficie d'un champ ne peut pas dépasser 50 % de la superficie totale de la ferme")
    private double superficie;
    private String fermeId;
    @Size(max = 10, message = "Un champ ne peut pas contenir plus de 10 arbres")
    private Set<String> arbresIds;

}
