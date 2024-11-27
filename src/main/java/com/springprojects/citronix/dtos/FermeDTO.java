package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


public record FermeDTO(
        Long id,
        @NotBlank(message = "Le nom de la ferme ne peut pas être vide") String nom,
        @NotBlank(message = "La localisation ne peut pas être vide") String localisation,
        @DecimalMin(value = "1.0", message = "La surface de la ferme doit être supérieure à 1,0 hectare") double superficie,
        @PastOrPresent(message = "La date de création doit être dans le passé ou le présent") LocalDate dateDeCreation) {
}



