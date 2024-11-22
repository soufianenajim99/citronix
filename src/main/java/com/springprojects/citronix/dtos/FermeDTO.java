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


@Builder
@Data
public class FermeDTO {
    private UUID id;
    @NotBlank(message = "Ferme name cannot be blank")
    private String nom;
    @NotBlank(message = "Localisation cannot be blank")
    private String localisation;
    @DecimalMin(value = "1", message = "Ferme surface must be greater than 1 hectares")
    private double superficie;
    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate dateCreation;
    private Set<ChampDTO> champs;
}
