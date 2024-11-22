package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.Vente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class RecolteDTO {
    private UUID id;
    @NotBlank(message = "La saison de la récolte est obligatoire")
    private String saison;
    @NotNull(message = "La date de récolte est obligatoire")
    @PastOrPresent(message = "La date de récolte doit être dans le passé ou aujourd'hui")
    private LocalDate dateRecolte;
    private Set<DetailRecoltesDTO> recolteDetails;
    private Set<VenteDTO> venteDetails;
}
