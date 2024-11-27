package com.springprojects.citronix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "champs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du champ ne peut pas être vide")
    private String nom;

    @DecimalMin(value = "0.1", message = "La surface du champ doit être d'au moins 0,1 hectare")
    @Column(nullable = false)
    private Double superficie;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;
}

