package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.DetailRecolteArbre;
import com.springprojects.citronix.models.Vente;
import com.springprojects.citronix.utils.enums.Saison;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record RecolteDTO(

        Long id,
        @NotNull(message = "La saison est obligatoire") Saison saison,
        @NotNull(message = "La date de la récolte est obligatoire") LocalDate date,
        @NotNull(message = "L'ID du champ est obligatoire") Long champId,
        @Valid List<@NotNull(message = "Les détails de récolte des arbres sont obligatoires") DetailRecolteArbre> detailsRecolteArbres) {
}


