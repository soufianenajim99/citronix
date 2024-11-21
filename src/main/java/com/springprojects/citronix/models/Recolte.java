package com.springprojects.citronix.models;

import com.springprojects.citronix.utils.enums.Saison;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "recoltes")
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Saison saison;

    private LocalDate dateRecolte;

    @OneToMany(mappedBy = "recolte")
    Set<DetailRecoltes> recolteDetails;

    @OneToMany(mappedBy = "recolte")
    Set<Vente> venteDetails;

}
