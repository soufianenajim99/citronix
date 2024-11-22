package com.springprojects.citronix.dtos;

import com.springprojects.citronix.models.Vente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ClientDTO {
    private UUID id;
    @NotBlank(message = "Le nom du client est obligatoire")
    private String nom;

    @Email(message = "L'adresse email du client doit être valide")
    private String email;
    private Set<VenteDTO> venteDetails;
}
