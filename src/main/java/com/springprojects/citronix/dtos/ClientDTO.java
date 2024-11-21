package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.Vente;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ClientDTO {
    private UUID id;
    private String nom;
    private String email;
    private Set<VenteDTO> venteDetails;
}
