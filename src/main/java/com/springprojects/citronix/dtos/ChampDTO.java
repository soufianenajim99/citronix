package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.Arbre;
import com.springprojects.citronix.models.Ferme;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ChampDTO {
    private UUID id;
    private String nom;
    private double superficie;
    private FermeDTO ferme;
    private Set<ArbreDTO> arbres;
}
