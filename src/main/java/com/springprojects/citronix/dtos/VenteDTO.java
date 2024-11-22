package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class VenteDTO {
    private UUID id;
    @NotNull(message = "Un client doit être associé à la vente")
    private String clientId;

    @NotNull(message = "Une récolte doit être associée à la vente")
    private String recolteId;

    @DecimalMin(value = "0.1", message = "La quantité vendue doit être supérieure à 0,1 kg")
    private double quantite;

    @DecimalMin(value = "0.01", message = "Le prix unitaire doit être supérieur à 0,01")
    private double prixUnitaire;

    @NotNull(message = "La date de vente est obligatoire")
    @PastOrPresent(message = "La date de vente doit être dans le passé ou aujourd'hui")
    private LocalDate dateVente;
}
