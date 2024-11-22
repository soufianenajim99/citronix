package com.springprojects.citronix.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    private double superficie;
    private FermeDTO ferme;
    private Set<ArbreDTO> arbres;


    public ChampDTO withFermeId(String fermeId) {
        return ChampDTO.builder()
                .id(this.id)
                .nom(this.nom)
                .superficie(this.superficie)
                .ferme(FermeDTO.builder().id(UUID.fromString(fermeId)).build())
                .build();
    }
}
