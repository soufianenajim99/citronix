package com.springprojects.citronix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "detailrecoltes")
public class DetailRecoltes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "arbre_id")
    Arbre arbre;

    @ManyToOne
    @JoinColumn(name = "recolte_id")
    Recolte recolte;

    private double quantite;
}
