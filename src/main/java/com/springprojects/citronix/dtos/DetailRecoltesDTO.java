package com.springprojects.citronix.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class DetailRecoltesDTO {
    private UUID id;
    private ArbreDTO arbre;
    private RecolteDTO recolte;
    private double quantite;
}
