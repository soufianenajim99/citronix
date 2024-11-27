package com.springprojects.citronix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteArbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbre arbre;

    private double quantite;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;
}

