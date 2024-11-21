package com.springprojects.citronix.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class VenteDTO {
    private UUID id;
    private ClientDTO client;
    private RecolteDTO recolte;
    private double quantite;
    private double prixUnitaire;
    private LocalDate dateVente;
}
