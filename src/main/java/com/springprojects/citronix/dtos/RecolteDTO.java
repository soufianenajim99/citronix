package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.Vente;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class RecolteDTO {
    private UUID id;
    private String saison;
    private LocalDate dateRecolte;
    private Set<DetailRecoltesDTO> recolteDetails;
    private Set<VenteDTO> venteDetails;
}
