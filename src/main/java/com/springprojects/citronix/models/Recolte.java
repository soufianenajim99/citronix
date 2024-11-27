package com.springprojects.citronix.models;

import com.springprojects.citronix.utils.enums.Saison;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@NoArgsConstructor
@AllArgsConstructor
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Saison saison;

    @NotNull(message = "La date de récolte est obligatoire")
    private LocalDate date;

    private double quantite;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolteArbre> detailsRecolteArbres;

    @OneToOne(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vente vente;
}

