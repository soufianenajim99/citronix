package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.DetailRecoltes;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ArbreDTO {
    private UUID id;
    private LocalDate dateCreation;
    private int age;
    private ChampDTO champ;
    private Set<DetailRecoltes> recoltedetails;
}
