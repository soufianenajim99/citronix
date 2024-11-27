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

