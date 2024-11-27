package com.springprojects.citronix.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la ferme ne peut pas être vide")
    private String nom;

    @NotBlank(message = "La localisation ne peut pas être vide")
    private String localisation;

    @DecimalMin(value = "0.1", message = "La surface de la ferme doit être supérieure à 0,1 hectare")
    @Column(nullable = false)
    private double superficie;

    @PastOrPresent(message = "La date de création doit être dans le passé ou le présent")
    @Column(nullable = false)
    private LocalDate dateDeCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL)
    private List<Champ> champs;
}

