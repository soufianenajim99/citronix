package com.springprojects.citronix.models;

import com.springprojects.citronix.utils.validation.ValidPlantingDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ValidPlantingDate
    @NotNull(message = "La date de plantation est obligatoire")
    private LocalDate dateDePlantation;

    @PositiveOrZero(message = "L'âge de l'arbre doit être nul ou positif")
    private int age;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    public double calculerProductivite() {
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        } else {
            return 0.0;
        }
    }

    public boolean estProductif() {
        return age <= 20;
    }
}

