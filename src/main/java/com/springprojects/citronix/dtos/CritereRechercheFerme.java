package com.springprojects.citronix.dtos;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CritereRechercheFerme(
        @Size(min = 1, max = 100) String nom,
        @Size(min = 1, max = 100) String localisation,
        @PositiveOrZero Double surfaceMin,
        @PositiveOrZero Double surfaceMax,
        @PastOrPresent LocalDate creeAvant,
        @PastOrPresent LocalDate creeApres
) {}
