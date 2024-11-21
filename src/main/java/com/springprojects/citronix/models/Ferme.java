package com.springprojects.citronix.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "fermes")
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL)
    private Set<Champ> champs;
}
