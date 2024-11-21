package com.springprojects.citronix.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class FermeDTO {
    private UUID id;
    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
    private Set<ChampDTO> champs;
}
