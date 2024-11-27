package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
public record VenteDTO(


        @Positive(message = "Le prix unitaire doit être supérieur à 0") double prixUnitaire,
        double quantite,
        @NotNull(message = "La date est obligatoire") @FutureOrPresent(message = "La date ne peut pas être dans le passé") LocalDate dateVente,
        String clientId,
        String recolteId) {
}
//
//@Data
//@Builder
//public class VenteDTO {
//    private UUID id;
//    private UUID clientId; // Reference to Client
//    private UUID recolteId; // Reference to Recolte
//    private double quantite;
//    private double prixUnitaire;
//    private LocalDate dateVente;
//}

//@Builder
//@Data
//public class VenteDTO {
//    private UUID id;
//
//    private ClientDTO client;
//    private RecolteDTO recolte;
//
//    @DecimalMin(value = "0.1", message = "La quantité vendue doit être supérieure à 0,1 kg")
//    private double quantite;
//
//    @DecimalMin(value = "0.01", message = "Le prix unitaire doit être supérieur à 0,01")
//    private double prixUnitaire;
//
//    @NotNull(message = "La date de vente est obligatoire")
//    @PastOrPresent(message = "La date de vente doit être dans le passé ou aujourd'hui")
//    private LocalDate dateVente;
//}
